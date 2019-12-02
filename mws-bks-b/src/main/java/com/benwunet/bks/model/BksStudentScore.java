package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BksStudentScore implements Serializable {
    private static final long serialVersionUID = 4577597041712201201L;
    /** ID */
    private Integer id;


    private String useerExamId;

    private String courseId;



    /** 考试成绩 */
    private String userScore;

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