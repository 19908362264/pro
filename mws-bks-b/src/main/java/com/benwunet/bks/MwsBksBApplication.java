package com.benwunet.bks;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.benwunet.bks.dao")
@RestController
public class MwsBksBApplication {

    public static void main(String[] args) {
        SpringApplication.run(MwsBksBApplication.class, args);
    }


    @GetMapping("/bks")
    public String test(){
     /*   System.out.println("来了老弟");
        List<ImcIan> ians = dao.selectList(null);
        ians.forEach(System.out::println);*/
        return "来了，老弟";
    }

}
