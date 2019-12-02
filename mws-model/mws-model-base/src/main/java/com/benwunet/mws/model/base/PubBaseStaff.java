package com.benwunet.mws.model.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 人员字典
 * @author FengChuan
 * @date 2019/4/22 18:12
 */
@Data
public class PubBaseStaff implements Serializable {
    private static final long serialVersionUID = -9131328430136928533L;

    /** id主键 */
    private Long id;

    /** 人员代码 */
    private String staffId;

    /** 人员姓名 */
    private String staffName;

    /** 性别:0:女1：男 */
    private String sex;

    /** 职务 */
    private String post;

    /** 身份证号码 */
    private String idCardNo;

    /** 开户行名称 */
    private String bankName;

    /** 账号 */
    private String accountNo;

    /** 电子邮箱 */
    private String email;

    /** 电话 */
    private String telephone;

    /** 传真 */
    private String fax;

    /** 顺序号 */
    private Integer orderNo;

    /** 是否使用:0：否；1：是 */
    private Boolean isUse;

    /** 录入时间 */
    private Date gmtCreate;

    /** 修改时间 */
    private Date gmtModified;

    /** 操作员代码 */
    private String operatorId;

    /** 操作员姓名 */
    private String operatorName;

    /** 开始使用时间 */
    private Date gmtStartup;

    /** 停止使用时间 */
    private Date gmtStop;

    /** 备注 */
    private String remark;

}