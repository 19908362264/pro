package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 考试平均分
 * </p>
 *
 * @author C
 * @since 2019-06-27
 */
@Data
public class BksExamAvgAchievement implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 区县ID
     */
    private String districtId;

    /**
     * 区县名称
     */
    private String districtName;

    /**
     * 学校ID
     */
    private String schoolId;

    /**
     * 区县名称
     */
    private String schoolName;

    /**
     * 我的成绩
     */
    private Double myAchievement;

    /**
     * 考试批次
     */
    private String examBatches;

    /**
     * 年级平均成绩
     */
    private Double gradeAvgAchievement;

    /**
     * 区县平均成绩
     */
    private Double districtAvgAchievement;

    /**
     * 省区市平均成绩
     */
    private Double provincesAvgAchievement;

    /**
     * 年级考试人数
     */
    private Long gradePersons;

    /**
     * 区县考试人数
     */
    private Long districtPersons;

    /**
     * 全市考试人数
     */
    private Long provincesPersons;

    /**
     * 录入时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 操作员代码
     */
    private String operatorId;

    /**
     * 操作员姓名
     */
    private String operatorName;

    private String schoolYears;

    /**
     * 备注
     */
    private String remark;

    private Double gradeAvgAchievementYw;

    private Double gradeAvgAchievementSx;

    private Double gradeAvgAchievementYy;

    private Double gradeAvgAchievementWl;

    private Double gradeAvgAchievementLs;

    private Double gradeAvgAchievementZz;

    private Double gradeAvgAchievementHx;

    private Double gradeAvgAchievementSw;

    private Double gradeAvgAchievementDl;

    private Double districtAvgAchievementYw;

    private Double districtAvgAchievementSx;

    private Double districtAvgAchievementYy;

    private Double districtAvgAchievementWl;

    private Double districtAvgAchievementLs;

    private Double districtAvgAchievementZz;

    private Double districtAvgAchievementHx;

    private Double districtAvgAchievementSw;

    private Double districtAvgAchievementDl;

    private Double provincesAvgAchievementYw;

    private Double provincesAvgAchievementSx;

    private Double provincesAvgAchievementYy;

    private Double provincesAvgAchievementWl;

    private Double provincesAvgAchievementLs;

    private Double provincesAvgAchievementZz;

    private Double provincesAvgAchievementHx;

    private Double provincesAvgAchievementSw;

    private Double provincesAvgAchievementDl;

    private Double avgAchievementYw;

    private Double avgAchievementSx;

    private Double avgAchievementYy;

    private Double avgAchievementWl;

    private Double avgAchievementLs;

    private Double avgAchievementZz;

    private Double avgAchievementHx;

    private Double avgAchievementSw;

    private Double avgAchievementDl;

    private Double gradeMinAchievementYw;

    private Double gradeMinAchievementSx;

    private Double gradeMinAchievementYy;

    private Double gradeMinAchievementWl;

    private Double gradeMinAchievementLs;

    private Double gradeMinAchievementZz;

    private Double gradeMinAchievementHx;

    private Double gradeMinAchievementSw;

    private Double gradeMinAchievementDl;

    private Double districtMinAchievementYw;

    private Double districtMinAchievementSx;

    private Double districtMinAchievementYy;

    private Double districtMinAchievementWl;

    private Double districtMinAchievementLs;

    private Double districtMinAchievementZz;

    private Double districtMinAchievementHx;

    private Double districtMinAchievementSw;

    private Double districtMinAchievementDl;

    private Double provincesMinAchievementYw;

    private Double provincesMinAchievementSx;

    private Double provincesMinAchievementYy;

    private Double provincesMinAchievementWl;

    private Double provincesMinAchievementLs;

    private Double provincesMinAchievementZz;

    private Double provincesMinAchievementHx;

    private Double provincesMinAchievementSw;

    private Double provincesMinAchievementDl;

    /**
     * 年级平均成绩
     */
    private Double gradeMinAchievement;

    /**
     * 区县平均成绩
     */
    private Double districtMinAchievement;

    /**
     * 省区市平均成绩
     */
    private Double provincesMinAchievement;

    /**
     * 年级平均成绩
     */
    private Double gradeMaxAchievement;

    /**
     * 区县平均成绩
     */
    private Double districtMaxAchievement;

    /**
     * 省区市平均成绩
     */
    private Double provincesMaxAchievement;

    private Double gradeMaxAchievementYw;

    private Double gradeMaxAchievementSx;

    private Double gradeMaxAchievementYy;

    private Double gradeMaxAchievementWl;

    private Double gradeMaxAchievementLs;

    private Double gradeMaxAchievementZz;

    private Double gradeMaxAchievementHx;

    private Double gradeMaxAchievementSw;

    private Double gradeMaxAchievementDl;

    private Double districtMaxAchievementYw;

    private Double districtMaxAchievementSx;

    private Double districtMaxAchievementYy;

    private Double districtMaxAchievementWl;

    private Double districtMaxAchievementLs;

    private Double districtMaxAchievementZz;

    private Double districtMaxAchievementHx;

    private Double districtMaxAchievementSw;

    private Double districtMaxAchievementDl;

    private Double provincesMaxAchievementYw;

    private Double provincesMaxAchievementSx;

    private Double provincesMaxAchievementYy;

    private Double provincesMaxAchievementWl;

    private Double provincesMaxAchievementLs;

    private Double provincesMaxAchievementZz;

    private Double provincesMaxAchievementHx;

    private Double provincesMaxAchievementSw;

    private Double provincesMaxAchievementDl;



    private Integer gradePersonYw;

    private Integer gradePersonSx;

    private Integer gradePersonYy;

    private Integer gradePersonWl;

    private Integer gradePersonLs;

    private Integer gradePersonZz;

    private Integer gradePersonHx;

    private Integer gradePersonSw;

    private Integer gradePersonDl;

    private Integer districtPersonYw;

    private Integer districtPersonSx;

    private Integer districtPersonYy;

    private Integer districtPersonWl;

    private Integer districtPersonLs;

    private Integer districtPersonZz;

    private Integer districtPersonHx;

    private Integer districtPersonSw;

    private Integer districtPersonDl;

    private Integer provincesPersonYw;

    private Integer provincesPersonSx;

    private Integer provincesPersonYy;

    private Integer provincesPersonWl;

    private Integer provincesPersonLs;

    private Integer provincesPersonZz;

    private Integer provincesPersonHx;

    private Integer provincesPersonSw;

    private Integer provincesPersonDl;



}
