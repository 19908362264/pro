package com.benwunet.bks.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "bks-project")
public interface BksFeign {

    @GetMapping("/users-anon/internal/bks")
    String getUserByUserCode() ;

}
