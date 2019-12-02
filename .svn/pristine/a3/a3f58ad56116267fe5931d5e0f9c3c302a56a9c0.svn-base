package com.benwunet.mws.model.notification;

import lombok.Data;

import java.io.Serializable;
/**
 *  @author WangLin
 *  @Date 2019/4/28 15:00
 */
@Data
public class PubSmsTemplate implements Serializable {

    private Integer id;
    // 产品名称:云通信短信API产品,开发者无需替换
    private String product;
    // 产品域名,开发者无需替换
    private String domain;
    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private String accessKeyId;
    private String accessKeySecret;
    // 可自助调整超时时间
    private String connectTimeout;
    private String readTimeout;
    // 必填:短信签名-可在短信控制台中找到
    private String signName;
    // 必填:短信模板-可在短信控制台中找到
    private String templateCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(String connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(String readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
