package com.benwunet.mws.user.service.impl;

import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.commons.utils.PageUtil;
import com.benwunet.mws.model.base.PubSysIpblacklist;
import com.benwunet.mws.user.dao.PubSysIpblacklistDao;
import com.benwunet.mws.user.service.PubSysIpblacklistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * ip黑名单实现类
 * @author xiangkaihong
 * @date 2019/5/5 14:07
 */
@Slf4j
@Service
public class PubSysIpblacklistServiceImpl implements PubSysIpblacklistService {

    /**
     * 保存
     * @param pubSysIpblacklist
     */

    @Autowired
    private PubSysIpblacklistDao pubSysIpblacklistDao;

    @Transactional
    @Override
    public void saves(PubSysIpblacklist pubSysIpblacklist) {
        PubSysIpblacklist pubSysIpblacklist01 = pubSysIpblacklistDao.findByIp(pubSysIpblacklist.getIp());
        if (pubSysIpblacklist01 != null) {
            throw new IllegalArgumentException(pubSysIpblacklist.getIp() + "已存在");
        }

        pubSysIpblacklistDao.saves(pubSysIpblacklist);
        log.info("添加黑名单ip:{}", pubSysIpblacklist);
    }

    @Transactional
    @Override
    public void deletes(String ip) {
        int n = pubSysIpblacklistDao.deletes(ip);
        if (n > 0) {
            log.info("删除黑名单ip:{}", ip);
        }
    }

    /**
     * 参数分页查询
     *
     * @param params
     */
    @Override
    public Page<PubSysIpblacklist> findIpblacklists(Map<String, Object> params) {
        int total = pubSysIpblacklistDao.count(params);
        List<PubSysIpblacklist> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, false);

            list = pubSysIpblacklistDao.findData(params);
        }
        return new Page<>(total, list);
    }
}
