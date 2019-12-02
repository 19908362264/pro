package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BksMajor implements Serializable {
    private static final long serialVersionUID = -8961012909736860963L;
    /** ID */
    private Integer id;

    /** 专业代码 */
    private String majorId;

    /** 学校代码 */
    private String schoolId;

    /** 专业类别代码 */
    private String majorCategoryId;

    /** 专业名称 */
    private String majorName;

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