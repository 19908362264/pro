package com.benwunet.mws.demo.feign;

import com.benwunet.mws.model.base.LoginPubSysUser;
import com.benwunet.mws.model.base.PubSysRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  @author WangLin
 *  @Date 2019/5/16 16:57
 */
@FeignClient(name = "user-center")
public interface UserClient {


    @GetMapping(value = "/users-anon/internal", params = "userName")
    LoginPubSysUser findByUserName(@RequestParam("userName") String userName);

    @GetMapping("/users-anon/internal/roles/{roleId}")
    PubSysRole findByRoleIdTest(@PathVariable String roleId);

}
