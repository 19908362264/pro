package com.benwunet.bks.controller;

import com.benwunet.bks.feign.BksFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private BksFeign bksFeign;

    @GetMapping("/feign")
    public String test(){
        return bksFeign.getUserByUserCode();
    }
}