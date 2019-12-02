package com.benwunet.mws.user.controller.bks;

import com.benwunet.mws.model.base.PubSysUser;
import com.benwunet.mws.model.exception.UserInformationDoesNotExistException;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.benwunet.mws.model.result.ResultErrorCode;
import com.benwunet.mws.user.feign.notification.SmsClient;
import com.benwunet.mws.user.service.PubSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bks/user")
public class UserController {
    @Autowired
    private SmsClient smsClient;
    @Autowired
    private PubSysUserService pubSysUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 备考生修改密码
     *
     * @param
     * @author zuoli
     * @date 2019-6-12
     */
    @PostMapping("/{userId}/setpwd")
    public ResponseResult setpassword(@PathVariable("userId") String userId, String userPwd, String oldPwd) {
        try {
            pubSysUserService.putPasswordByUserId(userId, oldPwd,userPwd);
            return ResponseResult.app(ResultCode.PT_OK, null, "密码修改成功", "");
        } catch (Exception e) {

            return ResponseResult.app(1, 1, e.getMessage(), "");
        }

    }





}
