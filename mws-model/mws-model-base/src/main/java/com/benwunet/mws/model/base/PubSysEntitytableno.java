package com.benwunet.mws.model.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统放号表
 * @author FengChuan
 * @date 2019/4/22 18:15
 */
@Data
public class PubSysEntitytableno implements Serializable {
    private static final long serialVersionUID = 3917744571264206407L;

    /** id主键 */
    private Long id;

    /** 实体表名称 */
    private String entityTable;

    /** 字段名称 */
    private String field;

    /** 前缀名 */
    private String prefix;

    /** 顺序号 */
    private Integer orderNo;

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