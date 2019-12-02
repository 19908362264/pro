package com.benwunet.bks.controller;

import com.benwunet.bks.feign.FileClient;
import com.benwunet.mws.model.file.PubFileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;

@RestController
@ApiIgnore
public class TestController {

    @GetMapping("/bks/test")
    public String test() {
        System.out.println("来了老弟");
        return "bks server";
    }

    @GetMapping("/users-anon/internal/bks")
    public String getUserByUserCode() {
        return "/users-anon/internal/bks";
    }

    @GetMapping("/bks-anon/test")
    public String test1() {
        return "测试接口";
    }

    @Autowired
    private FileClient fileClient;

    @PostMapping("/xxx")
    public PubFileInfo xx(@RequestPart(value = "file") MultipartFile file) throws IOException {
        try {
            PubFileInfo info = fileClient.upload(file);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
