package com.benwunet.mws.gateway.feign;

import com.benwunet.bks.model.BksUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/7/29 11:01
 */
@FeignClient(name = "bks-b")
@RequestMapping("/bks-b-anon/internal")
public interface BksBClient {

    @GetMapping("/bks/getUser/mobile")
    BksUser getUserBymobile(@RequestParam(value = "mobile", required = true) String mobile);

    @PutMapping("/bks/putUser/mobile")
    void updateLoginTime(@RequestParam(value = "mobile", required = true) String mobile);
}
