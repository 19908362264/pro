package com.benwunet.bks;

import com.benwunet.bks.dao.ImcIanDao;
import com.benwunet.bks.model.ImcIan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.benwunet.bks.dao")
@RestController
public class BksProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BksProjectApplication.class, args);
    }

    @Autowired
    private ImcIanDao dao;

    @GetMapping("/bks")
    public String test(){
//        System.out.println("来了老弟");
//        List<ImcIan> ians = dao.selectList(null);
//        ians.forEach(System.out::println);
        return "bks server";
    }
}
