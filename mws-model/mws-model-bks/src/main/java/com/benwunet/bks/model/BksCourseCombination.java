package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BksCourseCombination implements Serializable {

    private static final long serialVersionUID = -2616839835077066268L;
    /** ID */
    private Integer id;

    /** 学科组合代码 */
    private String courseCombinationId;

    /** 学科组合名称 */
    private String courseCombinationName;

    /** 学科组合热度排名 */
    private Integer courseHeatRanking;

    /** 可报专业比例 */
    private String proportion;

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