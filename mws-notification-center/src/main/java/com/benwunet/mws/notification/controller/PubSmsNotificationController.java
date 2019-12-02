package com.benwunet.mws.notification.controller;
import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.commons.utils.MobileUtils;
import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.notification.PubSmsNotification;
import com.benwunet.mws.model.notification.VerificationCode;
import com.benwunet.mws.notification.feign.UserClient;
import com.benwunet.mws.notification.service.PubSmsNotificationService;
import com.benwunet.mws.notification.service.VerificationCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 短消息发送控制器
 * @author xiangkaihong
 * @date 2019/5/12 11:40
 */

@RestController
@Api(value = "短信服务不需要token",tags = "短信服务不需要token")
public class PubSmsNotificationController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private UserClient userClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    @PostMapping(value = "/notification-anon/codes", params = { "mobile","key" })
    @ApiOperation(value ="获取短信验证码",notes = "获取短信验证码")
    public VerificationCode sendSmsVerificationCode(
           @ApiParam(value = "手机号",required = true) String mobile,
           @ApiParam(value = "reg:注册时；pro:找回密码",required = true) String key) {
        if (!MobileUtils.checkPhone(mobile)) {
            throw new IllegalArgumentException("手机号格式不正确");
        }
        if(key==null||key.equals("")||key.equals("reg")){
            PubSysUser userByUserMobile = userClient.getUserByUserMobile(mobile);
            if(userByUserMobile!=null){
                throw new IllegalArgumentException("该手机号已注册");
            }
        }else if(key.equals("pro")){
            PubSysUser userByUserMobile = userClient.getUserByUserMobile(mobile);
            if(userByUserMobile==null){
                throw new IllegalArgumentException("该手机号未注册");
            }
        }

        String sms = stringRedisTemplate.opsForValue().get("sms_" + mobile);
        if (!StringUtils.isEmpty(sms)){
            if (!StringUtils.equals(sms, "-1")){
                throw new IllegalArgumentException("验证码发送频率过快，请稍后重试！");
            }
        }

        VerificationCode verificationCode = verificationCodeService.generateCode(mobile);

        return verificationCode;
    }

    /**
     * 根据验证码和key获取手机号
     * @param key
     * @param code
     * @param delete
     * 是否删除key
     * @param second
     * 不删除时，可重置过期时间（秒）
     * @return
     */
    @GetMapping(value = "/notification-anon/internal/mobile", params = { "key", "code" })
    public String matcheCodeAndGetMobile(String key, String code, Boolean delete, Integer second) {
        return verificationCodeService.matcheCodeAndGetMobile(key, code, delete, second);
    }

    @Autowired
    private PubSmsNotificationService pubSmsNotificationService;


    /**
     * 查询短信发送记录
     * @param params
     * @return
     */
    @PreAuthorize("hasAuthority('sms:query')")
    @GetMapping("/sms")
    public Page<PubSmsNotification> findSms(@RequestParam Map<String, Object> params) {
        return pubSmsNotificationService.findSms(params);
    }
}
