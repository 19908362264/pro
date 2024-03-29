package com.benwunet.mws.log;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.benwunet.mws.log.dao")
public class LogCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run (LogCenterApplication.class, args);
    }
}
