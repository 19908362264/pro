package com.benwunet.mws.user.dao;

import com.benwunet.mws.model.base.PubSysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 *系统角色DAO
 * @author xiangkaihong
 * @date 2019/4/29 10:55
 */
@Mapper
public interface PubSysRoleDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    /**增加角色*/
    @Insert("insert into pub_sys_role(role_id, role_name, role_desc, order_no,is_use,operator_id,operator_name,gmt_startup,gmt_stop,remark)" +
            " values(#{roleId}, #{roleName}, #{roleDesc}, #{orderNo},#{isUse},#{operatorId},#{operatorName},#{gmtStartup},#{gmtStop},#{remark})")
    int saves(PubSysRole pubSysRole);

    /**修改角色名称*/
    @Update("update pub_sys_role t set t.role_name = #{roleName} where t.id = #{id}")
    int updates(PubSysRole pubSysRole);

    /**id 查询角色*/
    @Select("select * from pub_sys_role t where t.id = #{id}")
    PubSysRole findById(Long id);

    /**roleId 查询角色*/
    @Select("select * from pub_sys_role t where t.role_id = #{roleId}")
    PubSysRole findByRoleId(String roleId);

    /**删除角色*/
    @Delete("delete from pub_sys_role where role_id = #{roleId}")
    int deletes(String roleId);

    /**count角色记录数*/
    int count(Map<String, Object> params);

    /**参数查询角色*/
    List<PubSysRole> findData(Map<String, Object> params);

}
