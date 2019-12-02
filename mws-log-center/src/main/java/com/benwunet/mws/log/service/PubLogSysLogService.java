package com.benwunet.mws.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.model.log.PubLogSysLog;

import java.util.Map;

/**
 * 系统日志 Service
 * @author xaingkaihong
 * @date 2019/4/26 18:38
 */
public interface PubLogSysLogService extends IService<PubLogSysLog> {

    /**
     * 保存系统日志
     * @param pubLogSysLog
     */

    void saves(PubLogSysLog pubLogSysLog);

    /**查询日志*/
    Page<PubLogSysLog> findLogs(Map<String, Object> params);

}
