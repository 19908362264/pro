package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BksSchoolScore implements Serializable {
    private static final long serialVersionUID = 1149831416057657688L;
    /** ID */
    private Integer id;

    /** 分数线代码 */
    private String schoolScoreId;

    private String schoolId;
    private Integer type;
    private String max;
    private String min;
    private String year;
    private String average;
    private String min_section;
    private String province;
    private String batch;
    private String score;
    private String localBatchName;



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