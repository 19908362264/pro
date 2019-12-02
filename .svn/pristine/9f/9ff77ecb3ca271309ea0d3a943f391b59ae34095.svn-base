package com.benwunet.mws.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("notification-center")
public interface NotificationClint {

    @GetMapping(value = "/notification-anon/internal/mobile", params = { "key", "code" , "delete" , "second"})
    String matcheCodeAndGetMobile(@RequestParam(value = "key", required = true) String key, @RequestParam(value = "code", required = true) String code,
                                  @RequestParam(value = "delete", required = true) Boolean delete, @RequestParam(value = "second", required = true) Integer second) ;
}
