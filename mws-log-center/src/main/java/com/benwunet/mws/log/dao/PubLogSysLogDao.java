package com.benwunet.mws.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.benwunet.mws.model.log.PubLogSysLog;
import org.apache.ibatis.annotations.Insert;

import java.util.List;
import java.util.Map;

/**
 * 系统日志 Dao
 * @author xaingkaihong
 * @date 2019/4/26 18:38
 */
public interface PubLogSysLogDao extends BaseMapper<PubLogSysLog> {

    /**增加日志信息*/
    @Insert("user_name,module_name,params,is_flag,remark) values(#{userName},#{moduleName},#{params},#{isFlag},#{remark})")
    int saves(PubLogSysLog pubLogSysLog);

    /**count日志信息记录数*/
    int count(Map<String, Object> params);

    /**参数查询日志信息*/
    List<PubLogSysLog> findData(Map<String, Object> params);

}
