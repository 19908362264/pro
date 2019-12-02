package com.benwunet.mws.model.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门字典
 * @author FengChuan
 * @date 2019/4/22 18:11
 */
@Data
public class PubBaseDept implements Serializable {
    private static final long serialVersionUID = 812792633353642612L;

    /** id主键 */
    private Long id;

    /** 部门代码：单位代码(10位)+ 一级（2位） */
    private String deptId;

    /** 部门名称 */
    private String deptName;

    /** 上级部门编号:R：根节点 */
    private String supDeptId;

    /** 单位代码 */
    private String unitId;

    /** 级次 */
    private Integer gradation;

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