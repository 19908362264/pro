package com.benwunet.mws.log.controller;

import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.log.service.PubLogSysLogService;
import com.benwunet.mws.model.log.PubLogSysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PubLogSysLogController {

    @Autowired
    PubLogSysLogService service;

    /**日志保存*/
    @PostMapping("/logs-anon/internal")
    public void saves(@RequestBody PubLogSysLog pubLogSysLog) {

        service.save(pubLogSysLog);
    }

    /**
     * 日志查询
     * @param params
     * @return
     */
    @PreAuthorize("hasAuthority('log:query')")
    @GetMapping("/logs")
    public Page<PubLogSysLog> findLogs(@RequestParam Map<String, Object> params) {
        return service.findLogs(params);
    }


}
