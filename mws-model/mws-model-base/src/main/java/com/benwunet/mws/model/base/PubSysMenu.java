package com.benwunet.mws.model.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统功能菜单表
 * @author FengChuan
 * @date 2019/4/22 18:16
 */
@Data
public class PubSysMenu implements Serializable {
    private static final long serialVersionUID = -7377290868289863851L;

    /** id主键 */
    private Long id;

    /** 功能菜单代码 */
    private String menuId;

    /** 功能菜单类型:M：菜单(Menu);B：(Button)按钮;P：(Picture)图标;G：(Grid)表格;U：地址(URL);T：页面标记(Tag);L:分隔线 */
    private String menuTypeId;

    /** 功能菜单名称 */
    private String menuName;

    /** 功能菜单描述 */
    private String menuDesc;

    /** 功能菜单链接 */
    private String menuUrl;

    /** 功能菜单对应代码 */
    private String menuToNo;

    /** 上级功能菜单代码 */
    private String supMenuId;

    /** 功能菜单图标 */
    private String menuIco;

    /** 是否末级节点:0：否；1：是 */
    private Boolean isLastNode;

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