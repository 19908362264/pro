package com.benwunet.mws.notification.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.benwunet.mws.commons.model.Page;
import com.benwunet.mws.model.notification.PubSmsNotification;

import java.util.Map;

/**
 * 短消息发送Service
 * @author xiangkaihong
 * @date 2019/5/12 11:10
 */
public interface PubSmsNotificationService extends IService<PubSmsNotification> {

    void saves(PubSmsNotification pubSmsNotification, Map<String, String> params);

    void updates(PubSmsNotification pubSmsNotification);

    PubSmsNotification findById(Long id);

    Page<PubSmsNotification> findSms(Map<String, Object> params);

    /**
     * 发送短信
     */
    SendSmsResponse sendSmsMsg(PubSmsNotification pubSmsNotification);
}


