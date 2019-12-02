package com.benwunet.mws.user.controller.bks;

import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.exception.UserInformationDoesNotExistException;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.benwunet.mws.user.feign.notification.SmsClient;
import com.benwunet.mws.user.service.PubSysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users-anon/")
@Api(value = "备考生找回密码(小程序找回密码修改密码)",tags = "备考生找回密码(小程序找回密码修改密码)")
public class AnonController {

    @Autowired
    private SmsClient smsClient;
    @Autowired
    private PubSysUserService pubSysUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 备考生找回密码
     * @param
     * @author zuoli
     * @date 2019-6-12
     */
    @PostMapping("/findPassword")
    @ApiOperation(value = "备考生找回密码(小程序找回密码修改密码)不需要token",notes = "备考生找回密码(小程序找回密码修改密码)不需要token")
    public ResponseResult findPassword(
            @ApiParam(value = "手机号码",required = true) String mobile,
            @ApiParam(value = "密码",required = true)String password,
            @ApiParam(value = "验证码",required = true)String code,
            @ApiParam(value = "点击验证码后的key",required = true)String key) {
        try{
        PubSysUser user = pubSysUserService.getUserBySymbol(mobile);
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
        //修改手机号
            pubSysUserService.updatePassword(user.getUserId(),passwordEncoder.encode(password));
        }catch (Exception e){
            return ResponseResult.app(1, 1,e.getMessage(), "");
        }

        return ResponseResult.app(ResultCode.PT_OK, null, "修改成功", "");
    }

}
