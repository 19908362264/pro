package com.benwunet.mws.gateway.feign;

import com.benwunet.mws.model.log.PubLogSysLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 系统日志消费端
 * @author xiangkaihong
 * @date 2019/5/4 10:49
 */

@FeignClient("log-center")
public interface SysLogClient {

	@PostMapping("/logs-anon/internal")
	void save(@RequestBody PubLogSysLog pubLogSysLog);
}
