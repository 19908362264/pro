package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BksMajorScore implements Serializable {
    private static final long serialVersionUID = 208876588681764212L;
    /** ID */
    private Integer id;

    /** 专业分数线代码 */
    private String majorScoreId;

    /** 省市区代码 */
    private String provincesId;

    /** 学科代码 */
    private String subjectId;

    /** 专业代码 */
    private String majorId;

    /** 招生年份 */
    private String recruitingYear;

    /** 录取批次 */
    private String enrollBatch;

    /** 计划招生 */
    private String planRecruitStudent;

    /** 最高分 */
    private String maxScore;

    /** 最低分 */
    private String minScore;

    /** 平均分 */
    private String avgScore;

    /** 最低位次 */
    private String minimum;

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