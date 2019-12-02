package com.benwunet.mws.notification.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.commons.utils.PageUtil;
import com.benwunet.mws.model.notification.PubSmsNotification;
import com.benwunet.mws.notification.dao.PubSmsNotificationDao;
import com.benwunet.mws.notification.service.PubSmsNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 短消息发送实现类
 * @author xiangkaihong
 * @date 2019/5/12 11:21
 */
@Slf4j
@Service
public class PubSmsNotificationServiceImpl extends ServiceImpl<PubSmsNotificationDao,PubSmsNotification> implements PubSmsNotificationService {

    @Autowired
    private IAcsClient acsClient;
    @Value("${aliyun.sign.name1}")
    private String signName;
    @Value("${aliyun.template.code1}")
    private String templateCode;

    /**
     * 异步发送阿里云短信
     * @param pubSmsNotification
     * @return
     */
    @Async
    @Override
    public SendSmsResponse sendSmsMsg(PubSmsNotification pubSmsNotification) {
        if (pubSmsNotification.getSignName() == null) {
            pubSmsNotification.setSignName(this.signName);
        }

        if (pubSmsNotification.getTemplateCode() == null) {
            pubSmsNotification.setTemplateCode(this.templateCode);
        }

        /**阿里云短信官网demo代码*/
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(pubSmsNotification.getOperatorId());
        request.setSignName(pubSmsNotification.getSignName());
        request.setTemplateCode(pubSmsNotification.getTemplateCode());
        request.setTemplateParam(pubSmsNotification.getParams());
        request.setOutId(pubSmsNotification.getId().toString());
        request.setPhoneNumbers(pubSmsNotification.getMobile());
        SendSmsResponse response = null;
        try {
            response = acsClient.getAcsResponse(request);
            if (response != null) {
                log.info("发送短信结果：code:{}，message:{}，requestId:{}，bizId:{}",
                response.getCode(), response.getMessage(),
                response.getRequestId(), response.getBizId());
                pubSmsNotification.setRtnCode(response.getCode());
                pubSmsNotification.setRtnMessage(response.getMessage());
                pubSmsNotification.setRtnId(response.getBizId());
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }

        updates(pubSmsNotification);

        return response;
    }

    /**
     * 保存短信记录
      * @param pubSmsNotification
     * @param params
     */
    @Transactional
    @Override
    public void saves(PubSmsNotification pubSmsNotification, Map<String, String> params) {
        if (!CollectionUtils.isEmpty(params)) {
            pubSmsNotification.setParams(JSONObject.toJSONString(params));
        }

        pubSmsNotification.setGmtCreate(new Date());
        pubSmsNotification.setGmtModified(pubSmsNotification.getGmtCreate());
        pubSmsNotification.setSendDate(pubSmsNotification.getGmtCreate());

        baseMapper.saves(pubSmsNotification);
    }

    @Transactional
    @Override
    public void updates(PubSmsNotification pubSmsNotification) {
        pubSmsNotification.setGmtModified(new Date());
        baseMapper.updates(pubSmsNotification);
    }

    @Override
    public PubSmsNotification findById(Long id) {
        return baseMapper.findById(id);
    }

    @Override
    public Page<PubSmsNotification> findSms(Map<String, Object> params) {
        int total = baseMapper.count(params);
        List<PubSmsNotification> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);

            list = baseMapper.findData(params);
        }
        return new Page<>(total, list);
    }
}
