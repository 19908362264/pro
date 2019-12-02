package com.benwunet.mws.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.commons.utils.PageUtil;
import com.benwunet.mws.log.dao.PubLogSysLogDao;
import com.benwunet.mws.log.service.PubLogSysLogService;
import com.benwunet.mws.model.log.PubLogSysLog;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统日志存储到 Mysql 实现
 * @author xaingkaihong
 * @date 2019/4/27 11:38
 */
//@Primary
@Service
public class PubLogSysLogServiceImpl extends ServiceImpl<PubLogSysLogDao, PubLogSysLog> implements PubLogSysLogService {

     /**
     * 将系统日志保存到数据库
     * 注解@Async是开启异步执行
     * @param   pubLogSysLog
     */

    @Async
    @Override
    public void saves(  PubLogSysLog  pubLogSysLog) {
        if (pubLogSysLog.getGmtCreate() == null) {
            pubLogSysLog.setGmtCreate(new Date());
        }
        if (pubLogSysLog.getIsFlag() == null) {
            pubLogSysLog.setIsFlag(Boolean.TRUE);
        }

        baseMapper.saves(pubLogSysLog);
    }

    @Override
    public Page<PubLogSysLog> findLogs(Map<String, Object> params) {
        int total = baseMapper.count(params);
        List<PubLogSysLog> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);

            list =baseMapper.findData(params);
        }
        return new Page<>(total, list);
    }

}
