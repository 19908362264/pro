package com.benwunet.mws.demo.controller;

import com.benwunet.mws.demo.service.TestService;
import com.benwunet.mws.model.base.LoginPubSysUser;
import com.benwunet.mws.model.base.PubSysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private AmqpTemplate rabbitTemplate;


    @GetMapping(value = "/users/test", params = "userName")
    public LoginPubSysUser findByUserName(String userName) {
        return testService.findByUserName(userName);
    }

    @GetMapping(value = "/users/role/test/{roleId}")
    public PubSysRole findByRoleIdTest(@PathVariable String roleId) {
        return testService.findByRoleIdTest(roleId);
    }



}
