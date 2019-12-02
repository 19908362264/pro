package com.benwunet.mws.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient //服务发现
@MapperScan("com.benwunet.mws.user.dao")
/**
 * 用户中心
 * @author FengChuan
 * @date 2019/4/26 10:47
 */
@RestController
public class UserCenterApplication {

//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @GetMapping("/password")
//    public String getpasswordEncoder(){
//        return passwordEncoder.encode("1");
//    }

    public static void main(String[] args) {

        SpringApplication.run(UserCenterApplication.class, args);
    }
}
