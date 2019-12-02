package com.benwunet.mws.user.service.impl;

import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.commons.utils.PageUtil;
import com.benwunet.mws.model.base.PubSysMenu;
import com.benwunet.mws.user.dao.PubSysMenuDao;
import com.benwunet.mws.user.dao.PubSysRolemenuDao;
import com.benwunet.mws.user.service.PubSysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 系统功能菜单实现类
 * @author xiangkaihong
 * @date 2019/5/3 9:55
 */

@Slf4j
@Service
public class PubSysMenuServiceImpl implements PubSysMenuService {

    @Autowired
    private PubSysMenuDao pubSysMenuDao;

    @Autowired
    private PubSysRolemenuDao pubSysRolemenuDao;

    /**
     * 根据角色roleids获取功能菜单集合
     * @author xiangkaihong
     * @date 2019/5/3 8:34
     */
    @Override
    public Set<PubSysMenu> findByRoleIds(Set<String> roleIds) {
        return pubSysRolemenuDao.findRolemenuByRoleIds(roleIds);
    }

    /**
     * 保存
     * @author xiangkaihong
     * @date 2019/5/3 8:38
     */
    @Transactional
    @Override
    public void saves(PubSysMenu pubSysMenu) {
        PubSysMenu pubSysMenu01 = pubSysMenuDao.findByMenuId(pubSysMenu.getMenuId());
        if (pubSysMenu01 != null) {
            throw new IllegalArgumentException("功能菜单ID已存在");
        }
        pubSysMenu.setGmtCreate(new Date());
        pubSysMenu.setGmtModified(pubSysMenu.getGmtCreate());

        pubSysMenuDao.saves(pubSysMenu);
        log.info("保存功能菜单：{}", pubSysMenu);
    }

    /**
     * 修改
     * @author xiangkaihong
     * @date 2019/5/3 8:38
     */
    @Transactional
    @Override
    public void updates(PubSysMenu pubSysMenu) {
        pubSysMenu.setGmtModified(new Date());
        pubSysMenuDao.updates(pubSysMenu);
        log.info("修改功能菜单：{}", pubSysMenu);
    }

    /**
     * 删除
     * @author xiangkaihong
     * @date 2019/5/3 8:42
     */
    @Transactional
    @Override
    public void deletes(String menuId) {
        PubSysMenu pubSysMenu = pubSysMenuDao.findByMenuId(menuId);
        if (pubSysMenu == null) {
            throw new IllegalArgumentException("功能菜单ID不存在");
        }

        pubSysMenuDao.deletes(menuId);
        pubSysRolemenuDao.deletePubSysRolemenu(menuId,null);
        log.info("删除角色功能菜单：{}", pubSysMenu);
    }

    /**
     * 参数分页查询功能菜单
     * @author xiangkaihong
     * @date 2019/5/3 8:48
     */
    @Override
    public Page<PubSysMenu> findMenuIds(Map<String, Object> params) {
        int total = pubSysMenuDao.count(params);
        List<PubSysMenu> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, false);

            list = pubSysMenuDao.findData(params);
        }
        return new Page<>(total, list);
    }
}
