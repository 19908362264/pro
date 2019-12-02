package com.benwunet.mws.model.notification;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  @author WangLin
 *  @Date 2019/4/28 15:15
 */
@Data
public class PubSmsNotification implements Serializable {

    private static final long serialVersionUID = 3768433328889629457L;

   // @ApiModelProperty(value = "id主键")
    private Long id;
   //@ApiModelProperty(value = "手机号")
    private String mobile;
    //@ApiModelProperty(value = "阿里云短信签名")
    private String signName;
    //@ApiModelProperty(value = "阿里云模板CODE")
    private String templateCode;
    //@ApiModelProperty(value = "参数")
    private String params;
    //@ApiModelProperty(value = "阿里云返回ID")
    private String rtnId;
    //@ApiModelProperty(value = "阿里云返回CODE")
    private String rtnCode;
    //@ApiModelProperty(value = "阿里云返回消息")
    private String rtnMessage;
    //@ApiModelProperty(value = "发送日期（冗余字段,便于统计某天的发送次数")
    private Date sendDate;
    //@ApiModelProperty(value = "录入时间")
    private Date gmtCreate;
    //@ApiModelProperty(value = "修改时间")
    private Date gmtModified;
    //@ApiModelProperty(value = "操作员代码")
    private String operatorId;
    //@ApiModelProperty(value = "操作员姓名")
    private String operatorName;
    //@ApiModelProperty(value = "备注")
    private String remark;

}
