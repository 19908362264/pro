package com.benwunet.mws.model.base;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应商字典
 * @author FengChuan
 * @date 2019/4/22 18:13
 */
@Data
public class PubBaseSupplier implements Serializable {
    private static final long serialVersionUID = -2017070856432853543L;

    /** id主键 */
    private Long id;

    /** 供应商代码 */
    private String supplierId;

    /** 供应商名称 */
    private String supplierName;

    /** 统一社会信用代码 */
    private String socialCreditNo;

    /** 企业类型 */
    private String enterpriseType;

    /** 法定代表人 */
    private String legalPerson;

    /** 成立日期 */
    private Date creatDate;

    /** 注册资本 */
    private BigDecimal regCapital;

    /** 登记机关 */
    private String regAuthority;

    /** 地址 */
    private String address;

    /** 邮编 */
    private String zipCode;

    /** 联系人 */
    private String contacts;

    /** 联系电话 */
    private String contactsTel;

    /** 传真 */
    private String fax;

    /** 电子邮箱 */
    private String email;

    /** 银行账号 */
    private String bankAccountNo;

    /** 所属城市 */
    private String city;

    /** 诚信级别 */
    private String integrityLevel;

    /** 是否公有：0：否；1：是 */
    private Boolean isPublic;

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