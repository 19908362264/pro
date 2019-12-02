package com.benwunet.mws.oauth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 短消息消费端
 * @author xiangkaihong
 * @date 2019/5/3 16:34
 */

@FeignClient(value = "notification-center")
public interface SmsClient {

    @GetMapping(value = "/notification-anon/internal/mobile", params = { "key", "code" })
    String matcheCodeAndGetMobile(@RequestParam("key") String key, @RequestParam("code") String code,
                                  @RequestParam(value = "delete", required = false) Boolean delete,
                                  @RequestParam(value = "second", required = false) Integer second);
}
