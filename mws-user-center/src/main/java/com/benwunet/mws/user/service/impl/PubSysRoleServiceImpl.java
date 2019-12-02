package com.benwunet.mws.user.service.impl;

import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.commons.utils.PageUtil;
import com.benwunet.mws.model.base.PubSysMenu;
import com.benwunet.mws.model.base.PubSysRole;
import com.benwunet.mws.model.base.constants.UserCenterMq;
import com.benwunet.mws.user.dao.PubSysRoleDao;
import com.benwunet.mws.user.dao.PubSysRolemenuDao;
import com.benwunet.mws.user.dao.PubSysUserroleDao;
import com.benwunet.mws.user.service.PubSysRoleService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
* 系统角色实现类
* @author xiangkaihong
* @date 2019/5/2 8:54
*/

@Slf4j
@Service
public class PubSysRoleServiceImpl implements PubSysRoleService {

    @Autowired
    private PubSysRoleDao pubSysRoleDao;

    @Autowired
    private PubSysUserroleDao pubSysUserroleDao;
    @Autowired
    private PubSysRolemenuDao pubSysRolemenuDao;
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 保存系统角色
     * @author xiangkaihong
     * @date 2019/5/2 20:34
     */
    @Transactional
    @Override
    public void saves(PubSysRole pubSysRole) {
        PubSysRole pubSysRole01 = pubSysRoleDao.findByRoleId(pubSysRole.getRoleId());
        if (pubSysRole01 != null) {
            throw new IllegalArgumentException("角色ID已存在");
        }

        pubSysRole.setGmtCreate(new Date());
        pubSysRole.setGmtModified(pubSysRole.getGmtCreate());

        pubSysRoleDao.saves(pubSysRole);
        log.info("保存系统角色：{}", pubSysRole);
    }

    /**
     * 修改系统角色
     * @author xiangkaihong
     * @date 2019/5/2 20:36
     */
    @Transactional
    @Override
    public void updates(PubSysRole pubSysRole) {
        pubSysRole.setGmtModified(new Date());

        pubSysRoleDao.updates(pubSysRole);
        log.info("修改系统角色：{}", pubSysRole);
    }


    /**
     * 删除系统角色
     * @author xiangkaihong
     * @date 2019/5/2 20:38
     */
    @Transactional
    @Override
    public void deletes(String roleId) {
        PubSysRole pubSysRole = pubSysRoleDao.findByRoleId(roleId);

        pubSysRoleDao.deletes(roleId);
        /**删除角色菜单所有该角色数据*/
        pubSysRolemenuDao.deletePubSysRolemenu(roleId,null);
        /**删除用户角色表所有该角色数据*/
        pubSysUserroleDao.deletePubSysUserrole(null,roleId);

        log.info("删除系统角色：{}", pubSysRole);

        /** 发布系统角色删除的消息*/
        amqpTemplate.convertAndSend(UserCenterMq.MQ_EXCHANGE_USER, UserCenterMq.ROUTING_KEY_ROLE_DELETE, roleId);
    }

    /**
     * 设置系统角色菜单权限
     * @author xiangkaihong
     * @date 2019/5/2 20:42
     * @param roleId
     * @param menuIds
     */
    @Transactional
    @Override
    public void setMenuToRole(String roleId, Set<String> menuIds) {
        PubSysRole pubSysRole = pubSysRoleDao.findByRoleId(roleId);
        if (pubSysRole == null) {
            throw new IllegalArgumentException("系统角色不存在");
        }

        /**查出系统角色对应的old菜单权限*/
        Set<String> oldmenuIds = pubSysRolemenuDao.findRolemenuByRoleIds(Sets.newHashSet(roleId)).stream()
                .map(p -> p.getMenuId()).collect(Collectors.toSet());

        /**需要添加的菜单权限*/
        Collection<String> addmenuIds = org.apache.commons.collections4.CollectionUtils.subtract(menuIds,
                oldmenuIds);
        if (!CollectionUtils.isEmpty(addmenuIds)) {
            addmenuIds.forEach(menuId -> {
                pubSysRolemenuDao.savePubSysRolemenu(menuId,roleId,"000000","超级管理员","");
            });
        }
        /**需要删除的权限*/
        Collection<String> deletemenuIds = org.apache.commons.collections4.CollectionUtils
                .subtract(oldmenuIds, menuIds);
        if (!CollectionUtils.isEmpty(deletemenuIds)) {
            deletemenuIds.forEach(menuId -> {
                pubSysRolemenuDao.deletePubSysRolemenu(menuId,roleId);
            });
        }

        log.info("给角色id：{}，分配权限：{}", roleId, menuIds);
    }

    /**
     * id查询系统角色信息
     * @author xiangkaihong
     * @date 2019/5/2 20:48
     */
    @Override
    public PubSysRole findById(Long id) {
        return pubSysRoleDao.findById(id);
    }

    /**
     * userId查询系统角色信息
     * @author xiangkaihong
     * @date 2019/5/2 20:48
     */
    @Override
    public PubSysRole findByRoleId(String roleId) {
        return pubSysRoleDao.findByRoleId(roleId);
    }

    /**
     * 分页参数查询系统角色信息
     * @author xiangkaihong
     * @date 2019/5/2 21:08
     */
    @Override
    public Page<PubSysRole> findRoles(Map<String, Object> params) {
        int total = pubSysRoleDao.count(params);
        List<PubSysRole> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, false);

            list = pubSysRoleDao.findData(params);
        }
        return new Page<>(total, list);
    }
    /**
     * roleId查询系统角色菜单
     * @author xiangkaihong
     * @date 2019/5/2 21:18
     */
    @Override
    public Set<PubSysMenu> findMenuByRoleIds(String roleId) {
        return pubSysRolemenuDao.findRolemenuByRoleIds(Sets.newHashSet(roleId));
    }
}
