package com.benwunet.mws.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 认证中心
 * @author xiangkaihong
 * @date 2019/5/3 16:17
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class OauthCenterApplication {
    public static void main(String[] args) {

        SpringApplication.run(OauthCenterApplication.class, args);
    }
}
