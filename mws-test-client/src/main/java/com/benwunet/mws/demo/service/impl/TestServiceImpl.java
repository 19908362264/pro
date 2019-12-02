package com.benwunet.mws.demo.service.impl;

import com.benwunet.mws.demo.feign.UserClient;
import com.benwunet.mws.demo.service.TestService;
import com.benwunet.mws.model.base.LoginPubSysUser;

import com.benwunet.mws.model.base.PubSysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private UserClient userClient;

    @Override
    public LoginPubSysUser findByUserName(String userName) {
        return userClient.findByUserName(userName);
    }

    @Override
    public PubSysRole findByRoleIdTest(String roleId) {
        return userClient.findByRoleIdTest(roleId);
    }
}
