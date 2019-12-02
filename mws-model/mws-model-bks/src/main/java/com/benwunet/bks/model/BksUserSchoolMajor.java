package com.benwunet.bks.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BksUserSchoolMajor implements Serializable {
    private static final long serialVersionUID = -1271882571024259675L;
    /** ID */
    private Integer id;

    private String userId;

    /** 学校代码 */
    private String schoolId;

    /** 专业代码 */
    private String majorId;

    //专家id
    private String professorId;



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
