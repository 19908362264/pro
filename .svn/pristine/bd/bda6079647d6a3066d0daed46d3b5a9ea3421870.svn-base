package com.benwunet.mws.oauth.feign;

import com.benwunet.mws.model.base.LoginPubSysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登录用户消费端
 * @author xiangkaihong
 * @date 2019/5/3 16:38
 */

@FeignClient(value = "user-center")
public interface SysUserClient {
    /**
     * @param userName
     * @return
     */
    @GetMapping(value = "/users-anon/internal", params = "userName")
    LoginPubSysUser findByUserName(@RequestParam("userName") String userName);

    /**
     * @param tempCode
     * @param openId
     */
    @GetMapping("/wechat/login-check")
    void wechatuserLoginCheck(@RequestParam("tempCode") String tempCode, @RequestParam("openId") String openId);
}
