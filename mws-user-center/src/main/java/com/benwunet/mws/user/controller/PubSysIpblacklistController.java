package com.benwunet.mws.user.controller;

import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.model.base.PubSysIpblacklist;
import com.benwunet.mws.model.log.PubLogSysLogAnnotation;
import com.benwunet.mws.user.service.PubSysIpblacklistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ip黑名单控制器
 * @author xiangkaihong
 * @date 2019/5/4 11:40
 */
@Slf4j
@RestController
public class PubSysIpblacklistController {

    @Autowired
    private PubSysIpblacklistService pubSysIpblacklistService;

    /**增加黑名单ip*/
    @PubLogSysLogAnnotation(module = "添加黑名单")
    @PreAuthorize("hasAuthority('121101')")
    @PostMapping("/ipbacklists")
    public void saves(@RequestBody PubSysIpblacklist pubSysIpblacklist) {
        pubSysIpblacklist.setGmtCreate(new Date());

        pubSysIpblacklistService.saves(pubSysIpblacklist);
    }

    /** 删除黑名单ip */
    @PubLogSysLogAnnotation(module = "删除黑名单")
    @PreAuthorize("hasAuthority('121102')")
    @DeleteMapping("/ipblacklists")
    public void deletes(String ip) {
        pubSysIpblacklistService.deletes(ip);
    }

    /**查询黑名单
     * @author xiangkaihong
     * @date 2019/5/4 11:42
     * @param params
     * @return
     */
    @PreAuthorize("hasAuthority('121104')")
    @GetMapping("/ipbacklists")
    public Page<PubSysIpblacklist> findIpblacklists(@RequestParam Map<String, Object> params) {
        return pubSysIpblacklistService.findIpblacklists(params);
    }

    /**
     * 查询黑名单
     * 可内网匿名访问
     * @author xiangkaihong
     * @date 2019/5/4 11:46
     * @param params
     * @return
     */
    @GetMapping("/users-anon/internal/ipblacklists")
    public Set<String> findAllIpblacklists(@RequestParam Map<String, Object> params) {
        Page<PubSysIpblacklist> page = pubSysIpblacklistService.findIpblacklists(params);
        if (page.getTotal() > 0) {
            return page.getData().stream().map(PubSysIpblacklist::getIp).collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}
