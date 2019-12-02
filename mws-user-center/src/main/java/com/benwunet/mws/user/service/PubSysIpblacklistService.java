package com.benwunet.mws.user.service;

import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.model.base.PubSysIpblacklist;

import java.util.Map;

/**
 * ip黑名单Service
 * @author xiangkaihong
 * @date 2019/5/4 11:31
 */
public interface PubSysIpblacklistService {

    /**保存*/
    void saves(PubSysIpblacklist pubSysIpblacklist);

    /**删除*/
    void deletes(String ip);

    /**参数分页查询*/
    Page<PubSysIpblacklist> findIpblacklists(Map<String, Object> params);

}
