package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BksMajorCategory implements Serializable {
    private static final long serialVersionUID = 5983907807114832184L;
    /** ID */
    private Integer id;

    /** 专业类别代码 */
    private String majorCategoryId;

    private String collegesId;
    /** 专业类别上级代码，0：父节点 */
    private String parentId;

    private  String level1;
    private  String level2;
    private  String level3;
    private  String level1Name;
    private  String level2Name;
    private  String level3Name;

    /** 专业类别名称 */
    private String majorCategoryName;

    /** 专业层次 */
    private String majorLevel;


    private String code;

    private String isWhat;
    private String learnWhat;
    private String doWhat;

    private String content;
    private String job;

    /** 专业人气 */
    private Integer majorCategoryHeatRanking;

    /** 授予学位 */
    private String bachelorDegree;

    /** 修业年限 */
    private String studyDuration;





    /** 选考（学科）建议 */
    private String subjectAdvise;

    /** 专业介绍 */
    private String introduce;

    /** 主要课程 */
    private String course;

    /** 能干什么 */
    private String effect;

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