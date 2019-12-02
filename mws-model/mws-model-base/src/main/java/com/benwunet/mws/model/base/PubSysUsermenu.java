package com.benwunet.mws.model.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户菜单表
 * @author FengChuan
 * @date 2019/4/22 18:22
 */
@Data
public class PubSysUsermenu implements Serializable {
    private static final long serialVersionUID = -4084258720816495775L;

    /** id主键 */
    private Long id;

    /** 用户代码 */
    private String userId;

    /** 功能菜单代码 */
    private String menuId;

    /** 是否临时授权:0：否；1：是 */
    private Boolean isTmpGrant;

    /** 开始有效日期 */
    private Date startValidDate;

    /** 结束有效日期 */
    private Date endValidDate;

    /** 录入时间 */
    private Date gmtCreate;

    /** 修改时间 */
    private Date gmtModified;

    /** 操作员代码 */
    private String operatorId;

    /** 操作员姓名 */
    private String operatorName;

    /** 备注 */
    private String remark;

}