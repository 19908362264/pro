package com.benwunet.mws.demo.config;

import com.benwunet.mws.model.base.WechatInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 微信接口配置
 * @author xiangkaihong
 * @data 2019-04-28 14:43
 */

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WechatConfig {

    private String domain;
    private Map<String, WechatInfo> infos;

}
