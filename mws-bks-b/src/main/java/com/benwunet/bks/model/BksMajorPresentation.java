package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BksMajorPresentation implements Serializable {
    private static final long serialVersionUID = 2020920453482083536L;
    /** ID */
    private Integer id;

    /** 专业介绍代码 */
    private String majorPresentationId;

    /** 专业代码 */
    private String majorId;

    private String majorCategoryId;

    private String schoolId;

    private String content;

    private String job;

    private String name;

    private String year;

    private String level1Name;

    private String level2Name;

    private String level3Name;

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