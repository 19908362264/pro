package com.benwunet.mws.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("bks-project")
@RequestMapping("/bks-anon/internal")
public interface BksClint {

    @PostMapping("/register")
    void relevance(@RequestParam(value = "userId", required = true) String userId,
                   @RequestParam(value = "mobile", required = true) String mobile,
                   @RequestParam(value = "type", required = true) String type);


    /**
    添加用户userId到专家表
     */
    @PostMapping("/addprofessor")
    void addprofessor(@RequestParam(value = "userId", required = true) String userId);

}
