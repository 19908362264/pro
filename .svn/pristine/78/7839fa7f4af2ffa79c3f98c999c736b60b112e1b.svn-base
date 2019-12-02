package com.benwunet.mws.user.controller;

import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.model.base.PubSysMenu;
import com.benwunet.mws.model.log.PubLogSysLogAnnotation;
import com.benwunet.mws.user.service.PubSysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统功能菜单控制器
 * @author xiangkaihong
 * @date 2019/5/3 14:17
 */

@Slf4j
@RestController
public class PubSysMenuController {

    @Autowired
    private PubSysMenuService pubSysMenuService;

    /**
     * 管理后台增加功能菜单
     * @author xiangkaihong
     * @date 2019/5/3 14:19
     * @param pubSysMenu
     * @return
     */
    @PubLogSysLogAnnotation(module = "增加功能菜单")
    @PreAuthorize("hasAuthority('back:permission:save')")
    @PostMapping("/menus")
    public PubSysMenu save(@RequestBody PubSysMenu pubSysMenu) {
        if (StringUtils.isBlank(pubSysMenu.getMenuId())) {
            throw new IllegalArgumentException("功能菜单ID不能为空");
        }
        if (StringUtils.isBlank(pubSysMenu.getMenuName())) {
            throw new IllegalArgumentException("功能菜单名称不能为空");
        }

        pubSysMenuService.saves(pubSysMenu);

        return pubSysMenu;
    }

    /**
     * 管理后台修改功能菜单
     * @author xiangkaihong
     * @date 2019/5/3 14:22
     * @param pubSysMenu
     * @return
     */
    @PubLogSysLogAnnotation(module = "修改功能菜单")
    @PreAuthorize("hasAuthority('back:permission:update')")
    @PutMapping("/menus")
    public PubSysMenu update(@RequestBody PubSysMenu pubSysMenu) {
        if (StringUtils.isBlank(pubSysMenu.getMenuName())) {
            throw new IllegalArgumentException("功能菜单名称不能为空");
        }

        pubSysMenuService.updates(pubSysMenu);

        return pubSysMenu;
    }

    /**
     * 管理后台删除功能菜单
     * @author xiangkaihong
     * @date 2019/5/3 14:25
     * @param menuId
     * @return
     */
    @PubLogSysLogAnnotation(module = "删除功能菜单")
    @PreAuthorize("hasAuthority('back:permission:delete')")
    @DeleteMapping("/menus/{menuId}")
    public void delete(@PathVariable String menuId) {
        pubSysMenuService.deletes(menuId);
    }

    /**
     * 查询所有的功能菜单
     * @author xiangkaihong
     * @date 2019/5/3 14:28
     * @param
     * @return
     */
    @PreAuthorize("hasAuthority('back:permission:query')")
    @GetMapping("/menus")
    public Page<PubSysMenu> findMenuIds(@RequestParam Map<String, Object> params) {
        return pubSysMenuService.findMenuIds(params);
    }
}
