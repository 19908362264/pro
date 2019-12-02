package com.benwunet.mws.demo.service;

import com.benwunet.mws.model.base.LoginPubSysUser;
import com.benwunet.mws.model.base.PubSysRole;
import org.springframework.web.bind.annotation.PathVariable;

public interface TestService {

    LoginPubSysUser findByUserName(String userName);

    PubSysRole findByRoleIdTest(@PathVariable String roleId);
}
