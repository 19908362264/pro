package com.benwunet.mws.model.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * IP地址黑名单实体类
 * @author xiangkaihong
 * @date 2019/5/4 10:36
 */
@Data
public class PubSysIpblacklist implements Serializable {

    private static final long serialVersionUID = -2508736774287905465L;

    /** id主键 */
    private Long id;

    /** ip地址 */
    private String ip;

    /** 录入时间 */
    private Date gmtCreate;

    /** 操作员代码 */
    private String operatorId;

    /** 操作员姓名 */
    private String operatorName;

    /** 备注 */
    private String remark;
}
