package com.benwunet.mws.user.service;


import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.model.base.PubSysMenu;
import com.benwunet.mws.model.base.PubSysRole;

import java.util.Map;
import java.util.Set;

/**
 *系统角色Service
 * @author xiangkaihong
 * @date 2019/5/1 11:44
 */

public interface PubSysRoleService {

    /**保存*/
    void saves(PubSysRole pubSysRole);

    /**修改*/
    void updates(PubSysRole pubSysRole);

    /**删除角色*/
    void deletes(String roleId);

    /**设置系统角色菜单*/
    void setMenuToRole(String roleId, Set<String> menuIds);

    /**id查询系统角色信息*/
    PubSysRole findById(Long id);

    /**roleId查询系统角色信息*/
    PubSysRole findByRoleId(String roleId);

    /**分页参数查询系统角色信息*/
    Page<PubSysRole> findRoles(Map<String, Object> params);

    /**roleId查询系统角色菜单*/
    Set<PubSysMenu> findMenuByRoleIds(String roleId);
}
