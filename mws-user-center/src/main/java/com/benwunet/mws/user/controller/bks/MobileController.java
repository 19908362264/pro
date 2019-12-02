package com.benwunet.mws.user.controller.bks;

import com.benwunet.mws.commons.utils.MobileUtil;
import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.base.PubSysUsercredentials;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.user.feign.notification.SmsClient;
import com.benwunet.mws.user.service.PubSysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/8/29 14:57
 */
@RestController
@RequestMapping
@Api(value = "备考生小程序修改手机号",tags = "备考生小程序修改手机号")
public class MobileController {
    @Autowired
    private PubSysUserService userService;
    @Autowired
    private SmsClient smsClient;


    @PostMapping("/checkMobile")
    public ResponseResult checkMobile(String mobile, String key, String code){
        PubSysUser user = userService.getUserBySymbol(mobile);
        if (user == null) {
            return ResponseResult.app(1, 1, "该用户未注册", "");
        }
        if(key==null){
            return ResponseResult.app(1, 1, "请先获取验证码", "");
        }
        String phone = smsClient.matcheCodeAndGetPhone(key, code, true, 60);
        if (phone == null || !phone.equals(mobile)) {
            return ResponseResult.app(1, 1, "验证码不正确", "");
        }
        return ResponseResult.app(0, 0, "验证成功", "");
    }

    @PutMapping("/bindMobile")
    public ResponseResult updateMobile(String userId,String mobile, String key, String code) throws Exception {

        boolean b = MobileUtil.checkMobile(mobile);
        if(!b){
            return ResponseResult.app(1, 1, "请输入正确的手机号码", "");
        }
        PubSysUser user = userService.getUserBySymbol(mobile);
        if (user != null) {
            return ResponseResult.app(1, 1, "该手机号已注册", "");
        }
        if(key==null){
            return ResponseResult.app(1, 1, "请先获取验证码", "");
        }

        String phone = smsClient.matcheCodeAndGetPhone(key, code, true, 60);
        if (phone == null || !phone.equals(mobile)) {
            return ResponseResult.app(1, 1, "验证码不正确", "");
        }
        userService.putMobileByUserId(mobile,userId);
        PubSysUsercredentials dentials = userService.findDentialsByUserId(userId);
        dentials.setUserName(mobile);
        userService.updateDential(dentials);

        return ResponseResult.app(0, 0, "绑定成功", "");

    }
}
