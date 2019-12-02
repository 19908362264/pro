package com.benwunet.mws.model.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 单位字典
 * @author FengChuan
 * @date 2019/4/22 18:14
 */
@Data
public class PubBaseUnit implements Serializable {
    private static final long serialVersionUID = -1623589230080283946L;

    /** id主键 */
    private Long id;

    /** 单位代码 */
    private String unitId;

    /** 单位名称 */
    private String unitName;

    /** 单位简称 */
    private String unitShort;

    /** 单位类型代码 */
    private String unitTypeId;

    /** 上级单位代码:R：根节点 */
    private String supUnitId;

    /** 单位连接串：上级单位连接串+单位代码+@ */
    private String unitIdStr;

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