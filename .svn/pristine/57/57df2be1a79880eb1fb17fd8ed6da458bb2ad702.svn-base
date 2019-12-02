package com.benwunet.mws.user.feign.notification;

import com.benwunet.mws.model.notification.VerificationCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "notification-center")
public interface SmsClient {

	@GetMapping(value = "/notification-anon/internal/mobile", params = { "key", "code" })
    String matcheCodeAndGetPhone(@RequestParam("key") String key, @RequestParam("code") String code,
                                 @RequestParam(value = "delete", required = false) Boolean delete,
                                 @RequestParam(value = "second", required = false) Integer second);


    @PostMapping(value = "/notification-anon/codes", params = {  "mobile","key"})
     VerificationCode sendSmsVerificationCode(@RequestParam("mobile") String mobile, @RequestParam("key") String key);
}
