package com.benwunet.mws.user.dao;

import com.benwunet.mws.model.base.PubSysIpblacklist;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * ip黑名单Dao
 * @author xiangkaihong
 * @date 2019/5/4 11:10
 */
@Mapper
public interface PubSysIpblacklistDao {

    /**增加*/
    @Insert("insert into pub_sys_ipblacklist(ip, operator_id,operator_name,remark) values(#{ip},#{operatorId},#{operatorName},#{remark})")
    int saves(PubSysIpblacklist pubSysIpblacklist);

    /**删除*/
    @Delete("delete from pub_sys_ipblacklist where ip = #{ip}")
    int deletes(String ip);

    /**ip查询*/
    @Select("select * from pub_sys_ipblacklist t where t.ip = #{ip}")
    PubSysIpblacklist findByIp(String ip);

    /**count ip黑名单记录数*/
    int count(Map<String, Object> params);
    /**参数查询ip黑名单*/
    List<PubSysIpblacklist> findData(Map<String, Object> params);

}
