package com.benwunet.mws.oauth.feign;

import com.benwunet.mws.model.log.PubLogSysLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 系统日志消费端
 * @author xiangkaihong
 * @date 2019/5/3 16:47
 */

@FeignClient(value = "log-center")
public interface SysLogClient {

//    @PostMapping("/logs-anon/internal")
//    void saves(@RequestBody PubLogSysLog pubLogSysLog) {
//       /**System.out.println(pubLogSysLog.toString());*/
//        pubLogSysLog.toString();
//    }
    @PostMapping("/logs-anon/internal")
    void saves(@RequestBody PubLogSysLog pubLogSysLog);
}
