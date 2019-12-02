package com.benwunet.mws.model.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表
 * @author FengChuan
 * @date 2019/4/22 18:20
 */
@Data
public class PubSysUser implements Serializable {

    private static final long serialVersionUID = 4066946661252994281L;

    /** id主键 */
    private Long id;

    /** 用户代码:单位简码(5位)+顺序号(3位)+预留2位 */
    private String userId;

    /** 用户姓名 */
    private String userName;

    /** 用户密码 */
    private String password;

    /** 用户类型 */
    private String userType;

    /** 单位代码 */
    private String unitId;

    /** 部门代码 */
    private String deptId;

    /** 性别:0:女1：男 */
    private String sex;

    /** 出生日期 */
    private Date birthday;

    /** 昵称 */
    private String nikeName;

    /** 头像 */
    private String headImg;

    /** 脸部图像 */
    private String faceImg;

    /** 身份证号 */
    private String cardNo;

    /** 证件类型 */
    private String cardType;

    /** 省份证正面图 */
    private String cardFrontImg;

    /** 省份证反面图 */
    private String cardBackImg;

    /** 职务 */
    private String post;

    /** 电子信箱 */
    private String email;

    /** 手机号 */
    private String mobile;

    /** 资格证号码 */
    private String certificateNo;

    /** 资格证图像 */
    private String certificateImg;

    /** 用户权限位：0：无；1：有；（1位：--功能组授权；2位：--单位授权；3位：--管理用户授权；（数据授权）4位：--资金来源指标；5位：--内部批复指标；等） */
    private String privilegeBit;

    /** 顺序号 */
    private Integer orderNo;

    /** 是否使用:0：否；1：是 */
    private Boolean isUse;

    /** 是否审核通过 */
    private Boolean isAudit;

    /** 卷踪实名认证通过 0 未实名认证 1 实名认证待审核 2实名认证已通过 3实名认证已驳回 */
    private Integer status;

    /** 审核意见 */
    private String auditOpinion;

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