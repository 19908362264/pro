package com.benwunet.mws.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 监控中心
 * @author xiangkaihong
 * @date 2019/5/4 7:59
 */

@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication

public class MonitorCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorCenterApplication.class, args);
    }

}
