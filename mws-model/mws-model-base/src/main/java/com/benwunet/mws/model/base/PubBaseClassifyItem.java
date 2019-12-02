package com.benwunet.mws.model.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公用基础分类项字典
 * @author FengChuan
 * @date 2019/4/22 18:04
 */
@Data
public class PubBaseClassifyItem implements Serializable {

    private static final long serialVersionUID = 7898941380110754745L;

    /** id主键 */
    private Long id;

    /** 单位代码+分类代码+2位顺序号 */
    private String classifyItemId;

    /** 分类项名称 */
    private String classifyItemName;

    /** 分类项描述 */
    private String classifyItemDesc;

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