package com.benwunet.mws.gateway.feign;
import com.benwunet.bks.model.BksUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bks-project")
@RequestMapping("/bks-anon/internal")
public interface BksClient {

    /**
     * @param mobile
     * @return
     */
    @GetMapping("/bks/getUser/mobile")
    BksUser getUserBymobile(@RequestParam(value = "mobile", required = true) String mobile);
}
