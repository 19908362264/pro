package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BksSubject implements Serializable {
    private static final long serialVersionUID = -5773832921116573814L;
    /** ID */
    private Integer id;

    /** 学科代码 */
    private String subjectId;

    /** 学校代码 */
    private String schoolId;

    /** 学科名称 */
    private String subjectName;

    /** 院系介绍 */
    private Date gmtCreate;

    /** 录入时间 */
    private Date gmtModified;

    /** 修改时间 */
    private String operatorId;

    /** 操作员代码 */
    private String operatorName;

    /** 操作员姓名 */
    private String remark;

    /** 备注 */
    private String subjectContent;


}