package com.benwunet.mws.user.service;

import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.model.base.PubSysMenu;
import com.benwunet.mws.model.base.PubSysRolemenu;

import java.util.Map;
import java.util.Set;

/**
 * 系统功能菜单Service
 * 根据角色roleids获取功能菜单集合
 * @author xiangkaihong
 * @date 2019/5/1 13:17
 */
public interface PubSysMenuService {

    /**根据角色roleids获取功能菜单集合*/
    Set<PubSysMenu> findByRoleIds(Set<String> roleIds);

    /**保存*/
    void saves(PubSysMenu pubSysMenu);

    /**修改*/
    void updates(PubSysMenu pubSysMenu);

    /**删除*/
    void deletes(String menuId);

    /**参数分页查询功能菜单信息*/
    Page<PubSysMenu> findMenuIds(Map<String, Object> params);
}
