package com.benwunet.mws.user.controller;

import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.model.base.PubSysMenu;
import com.benwunet.mws.model.base.PubSysRole;
import com.benwunet.mws.model.log.PubLogSysLogAnnotation;
import com.benwunet.mws.user.service.PubSysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * 系统角色控制器
 * @author xiangkaihong
 * @date 2019/5/3 14:34
 */
@Slf4j
@RestController
public class PubSysRoleController {

    @Autowired
    private PubSysRoleService pubSysRoleService;

    /**
     * 管理后台增加角色
     * @author xiangkaihong
     * @date 2019/5/3 14:35
     * @param pubSysRole
     * @return  pubSysRole
     */

    @PubLogSysLogAnnotation(module = "增加角色")
    @PreAuthorize("hasAuthority('back:role:save')")
    @PostMapping("/roles")
    public PubSysRole save(@RequestBody PubSysRole pubSysRole) {
        if (StringUtils.isBlank(pubSysRole.getRoleId())) {
            throw new IllegalArgumentException("角色ID不能为空");
        }
        if (StringUtils.isBlank(pubSysRole.getRoleName())) {
            throw new IllegalArgumentException("角色名称不能为空");
        }

        pubSysRoleService.saves(pubSysRole);

        return pubSysRole;
    }

    /**
     * 管理后台删除角色
     * @author xiangkaihong
     * @date 2019/5/3 14:38
     * @param roleId
     * @return
     */
    @PubLogSysLogAnnotation(module = "删除角色")
    @PreAuthorize("hasAuthority('back:role:delete')")
    @DeleteMapping("/roles/{roleId}")
    public void deleteRole(@PathVariable String roleId) {
        pubSysRoleService.deletes(roleId);
    }

    /**
     * 管理后台修改角色
     * @author xiangkaihong
     * @date 2019/5/3 14:42
     * @param pubSysRole
     * @return pubSysRole
     */
    @PubLogSysLogAnnotation(module = "修改角色")
    @PreAuthorize("hasAuthority('back:role:update')")
    @PutMapping("/roles")
    public PubSysRole update(@RequestBody PubSysRole pubSysRole) {
        if (StringUtils.isBlank(pubSysRole.getRoleName())) {
            throw new IllegalArgumentException("角色名称不能为空");
        }

        pubSysRoleService.updates(pubSysRole);

        return pubSysRole;
    }

    /**
     * 管理后台给角色分配功能菜单
     * @author xiangkaihong
     * @date 2019/5/3 14:48
     * @param roleId      角色Id
     * @param menuIds     功能菜单Ids
     * @return
     */
    @PubLogSysLogAnnotation(module = "角色菜单授权")
    @PreAuthorize("hasAuthority('back:role:permission:set')")
    @PostMapping("/roles/{roleId}/menus")
    public void setPermissionToRole(@PathVariable String roleId, @RequestBody Set<String> menuIds) {
        pubSysRoleService.setMenuToRole(roleId, menuIds);
    }

    /**
     * 获取角色的菜单权限
     * @author xiangkaihong
     * @date 2019/5/3 14:48
     * @param roleId      角色Id
     * @return
     */
    @PreAuthorize("hasAnyAuthority('back:role:permission:set','role:permission:byroleid')")
    @GetMapping("/roles/{roleId}/menus")
    public Set<PubSysMenu> findMenuByRoleIds(@PathVariable String roleId) {
        return pubSysRoleService.findMenuByRoleIds(roleId);
    }

    /**
     * roleId查询系统角色信息
     * @author xiangkaihong
     * @date 2019/5/3 14:48
     * @param roleId      角色Id
     * @return
     */

    //@PreAuthorize("hasAuthority('120204')")
    @GetMapping("/roles/{roleId}")
    public PubSysRole findByRoleId(@PathVariable String roleId) {
        return pubSysRoleService.findByRoleId(roleId);
    }

   /**测试 xiangkaihong add */
    @GetMapping("/users-anon/internal/roles/{roleId}")
    public PubSysRole findByRoleIdTest(@PathVariable String roleId) {
        return pubSysRoleService.findByRoleId(roleId);
    }


    /**
     * 分页参数查询系统角色
     * @author xiangkaihong
     * @date 2019/5/3 14:58
     * @param params
     * @return
     */
    @PreAuthorize("hasAuthority('back:role:query')")
    @GetMapping("/roles")
    public Page<PubSysRole> findRoles(@RequestParam Map<String, Object> params) {
        return pubSysRoleService.findRoles(params);
    }

}
