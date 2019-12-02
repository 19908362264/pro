package com.benwunet.mws.user.controller.bksB;

import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.benwunet.mws.user.feign.notification.SmsClient;
import com.benwunet.mws.user.service.PubSysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users-anon/bks-b")
@Api(value ="备考生b端找回密码不需要token", description = "备考生b端找回密码不需要token")
public class BksBAnonController {

    @Autowired
    private SmsClient smsClient;
    @Autowired
    private PubSysUserService pubSysUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 备考生b端找回密码
     * @param
     * @author zuoli
     * @date 2019-8-19
     */
    @ApiOperation(value = "备考生b端找回密码不需要token",tags = "备考生b端找回密码不需要token")
    @PostMapping("/findPassword")
    public ResponseResult findPassword(
            @ApiParam(value = "用户手机号",required =true) String mobile,
            @ApiParam(value = "密码",required =true)String password,
            @ApiParam(value = "验证码",required =true)String code,
            @ApiParam(value = "点击获取验证码后返回的key",required =true) String key) {
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

        return ResponseResult.app(ResultCode.PT_OK, 0, "修改成功", "");
    }

}
