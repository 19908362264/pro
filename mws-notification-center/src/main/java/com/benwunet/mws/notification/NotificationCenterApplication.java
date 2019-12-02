package com.benwunet.mws.notification;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 通知中心
 * @author xiangkaihong
 * @date 2019/5/12 10:51
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.benwunet.mws.notification.dao")
public class NotificationCenterApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(NotificationCenterApplication.class, args);
    }

}
