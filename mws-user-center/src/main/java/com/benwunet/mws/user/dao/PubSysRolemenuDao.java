package com.benwunet.mws.user.dao;

import com.benwunet.mws.model.base.PubSysMenu;
import com.benwunet.mws.model.base.PubSysRolemenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 *角色功能菜单关系
 * @author xiangkaihong
 * @date 2019/4/29 17:29
 */

@Mapper
public interface PubSysRolemenuDao {

    /**增加角色功能菜单*/
    @Insert("insert into pub_sys_rolemenu (menu_id, role_id,operator_id,operator_name,remark) value(#{menuId}, #{roleId},#{operatorId},#{operatorName},#{remark})")
    int savePubSysRolemenu(@Param("menuId") String menuId, @Param("roleId") String roleId, @Param("operatorId") String operatorId,@Param("operatorName") String operatorName,@Param("remark") String remark);

    /**删除角色功能菜单*/
    int deletePubSysRolemenu(@Param("menuId") String menuId, @Param("roleId") String roleId);

    /**查询角色功能菜单*/
    Set<PubSysMenu> findRolemenuByRoleIds(@Param("roleIds") Set<String> roleIds);

}
