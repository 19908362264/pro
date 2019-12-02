package com.benwunet.mws.oauth.service.Impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * 将oauth_client_details表数据缓存到redis，毕竟该表改动非常小，而且数据很少，这里做个缓存优化
 * 如果有通过界面修改client的需求的话，不要用JdbcClientDetailsService，请用该类，否则redis里有缓存
 * 如果手动修改了该表的数据，请注意清除redis缓存，是hash结构，key是client_details
 * @author xiangkaihong
 * @date 2019/5/3 17:15
 */

@Slf4j
@Service
public class RedisClientDetailsService extends JdbcClientDetailsService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public RedisClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 缓存client的redis key，这里是hash结构存储
     * @author xiangkaihong
     * @date 2019/5/3 17:17
     */
    private static final String CACHE_CLIENT_KEY = "client_details";

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        ClientDetails clientDetails = null;

        /**先从redis获取*/
        String value = (String) stringRedisTemplate.boundHashOps(CACHE_CLIENT_KEY).get(clientId);
        if (StringUtils.isBlank(value)) {
            clientDetails = cacheAndGetClient(clientId);
        } else {
            clientDetails = JSONObject.parseObject(value, BaseClientDetails.class);
        }

        return clientDetails;
    }

    /**
     * 缓存client并返回client
     * @author xiangkaihong
     * @date 2019/5/3 17:19
     * @param clientId
     */
    private ClientDetails cacheAndGetClient(String clientId) {
        /**从数据库读取*/
        ClientDetails clientDetails = super.loadClientByClientId(clientId);
        /** 写入redis缓存*/
        if (clientDetails != null) {
            stringRedisTemplate.boundHashOps(CACHE_CLIENT_KEY).put(clientId, JSONObject.toJSONString(clientDetails));
            log.info("缓存clientId:{},{}", clientId, clientDetails);
        }

        return clientDetails;
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        super.updateClientDetails(clientDetails);
        cacheAndGetClient(clientDetails.getClientId());
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        super.updateClientSecret(clientId, secret);
        cacheAndGetClient(clientId);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        super.removeClientDetails(clientId);
        removeRedisCache(clientId);
    }

    /**
     *  删除redis缓存
     * @author xiangkaihong
     * @date 2019/5/3 17:25
     * @param clientId
     */
    private void removeRedisCache(String clientId) {
        stringRedisTemplate.boundHashOps(CACHE_CLIENT_KEY).delete(clientId);
    }

    /**
     *  将oauth_client_details全表刷入redis
     * @author xiangkaihong
     * @date 2019/5/3 17:29
     */
    public void loadAllClientToCache() {
        if (stringRedisTemplate.hasKey(CACHE_CLIENT_KEY).equals(Boolean.TRUE)) {
            return;
        }
        log.info("将oauth_client_details全表刷入redis");

        List<ClientDetails> list = super.listClientDetails();
        if (CollectionUtils.isEmpty(list)) {
            log.error("oauth_client_details表数据为空，请检查");
            return;
        }

        list.parallelStream().forEach(client -> {
            stringRedisTemplate.boundHashOps(CACHE_CLIENT_KEY).put(client.getClientId(), JSONObject.toJSONString(client));
        });
    }

}
