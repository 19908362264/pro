package com.benwunet.mws.oauth.controller;

import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.model.log.PubLogSysLogAnnotation;
import com.benwunet.mws.model.oauth.SystemClientInfo;
import com.benwunet.mws.oauth.service.Impl.RedisClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * client管理功能
 * @author xiangkaihong
 * @date 2019/5/3 17:59
 */

@Slf4j
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private RedisClientDetailsService clientDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PreAuthorize("hasAuthority('client:save')")
    @PubLogSysLogAnnotation(module = "保存client")
    @PostMapping
    public void save(@RequestBody BaseClientDetails clientDetails) {
        ClientDetails client = getAndCheckClient(clientDetails.getClientId(), false);
        if (client != null) {
            throw new IllegalArgumentException(clientDetails.getClientId() + "已存在");
        }
        /**密码加密*/
        clientDetails.setClientSecret(passwordEncoder.encode(clientDetails.getClientSecret()));

        clientDetailsService.addClientDetails(clientDetails);
        log.info("保存client信息：{}", clientDetails);
    }

    @PreAuthorize("hasAuthority('client:update')")
    @PubLogSysLogAnnotation(module = "修改client")
    @PutMapping
    public void update(@RequestBody BaseClientDetails clientDetails) {
        getAndCheckClient(clientDetails.getClientId(), true);
        clientDetailsService.updateClientDetails(clientDetails);
        log.info("修改client信息：{}", clientDetails);
    }

    @PreAuthorize("hasAuthority('client:update')")
    @PubLogSysLogAnnotation(module = "修改client密码")
    @PutMapping(value = "/{clientId}", params = "secret")
    public void updateSecret(@PathVariable String clientId, String secret) {
        getAndCheckClient(clientId, true);
        checkSystemClient(clientId);

        secret = passwordEncoder.encode(secret);
        clientDetailsService.updateClientSecret(clientId, secret);
        log.info("修改client密码：{},{}", clientId, secret);
    }

    @PreAuthorize("hasAuthority('client:query')")
    @GetMapping
    public Page<ClientDetails> findClients() {
        List<ClientDetails> clientDetails = clientDetailsService.listClientDetails();
        clientDetails.parallelStream().forEach(c -> isSystemClient(c));
        return new Page<>(clientDetails.size(), clientDetails);
    }

    @PreAuthorize("hasAuthority('client:query')")
    @GetMapping("/{clientId}")
    public ClientDetails getById(@PathVariable String clientId) {
        return getAndCheckClient(clientId, true);
    }

    @PreAuthorize("hasAuthority('client:del')")
    @PubLogSysLogAnnotation(module = "删除client")
    @DeleteMapping("/{clientId}")
    public void delete(@PathVariable String clientId) {
        getAndCheckClient(clientId, true);
        checkSystemClient(clientId);

        clientDetailsService.removeClientDetails(clientId);
        log.info("删除client：{}", clientId);
    }

    /**
     * 根据id获取client信息
     * @author xiangkaihong
     * @date 2019/5/3 18:10
     * @param clientId
     * @param check    是否校验存在性
     * @return
     */

     private ClientDetails getAndCheckClient(String clientId, boolean check) {
        ClientDetails clientDetails = null;
        try {
            clientDetails = clientDetailsService.loadClientByClientId(clientId);
            isSystemClient(clientDetails);
        } catch (NoSuchClientException e) {
            if (check) {
                throw new IllegalArgumentException(clientId + "不存在");
            }
        }

        return clientDetails;
    }

    private void checkSystemClient(String clientId) {
        if (SystemClientInfo.CLIENT_ID.equals(clientId)) {
            throw new IllegalArgumentException("不能操作系统数据");
        }
    }

    /**
     * 判断是否是我们自己系统内部用的client<br>
     * 在扩展字段里放一个isSystem标注一下
     * @author xiangkaihong
     * @date 2019/5/3 18:16
     * @param clientDetails
     * @see SystemClientInfo
     */
     private boolean isSystemClient(ClientDetails clientDetails) {
        BaseClientDetails baseClientDetails = (BaseClientDetails) clientDetails;
        Map<String, Object> additionalInformation = baseClientDetails.getAdditionalInformation();
        if (additionalInformation == null) {
            additionalInformation = new HashMap<>();
            baseClientDetails.setAdditionalInformation(additionalInformation);
        }

        boolean isSystem = SystemClientInfo.CLIENT_ID.equalsIgnoreCase(baseClientDetails.getClientId());
        baseClientDetails.addAdditionalInformation("isSystem", isSystem);

        return isSystem;
    }

}
