package com.benwunet.bks.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 考试平均分
 * </p>
 *
 * @author liushuangqing
 * @since 2019-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksExamAvgAchievement implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 专业代码
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

    private Double gradeAvgAchievementYw;

    private Double gradeAvgAchievementSx;

    private Double gradeAvgAchievementYy;

    private Double gradeAvgAchievementWl;

    private Double gradeAvgAchievementLs;

    private Double gradeAvgAchievementZz;

    private Double gradeAvgAchievementHx;

    private Double gradeAvgAchievementSw;

    private Double gradeAvgAchievementDl;

    private Double provincesAvgAchievementYw;

    private Double provincesAvgAchievementSx;

    private Double provincesAvgAchievementYy;

    private Double provincesAvgAchievementWl;

    private Double provincesAvgAchievementLs;

    private Double provincesAvgAchievementZz;

    private Double provincesAvgAchievementHx;

    private Double provincesAvgAchievementSw;

    private Double districtAvgAchievementYw;

    private Double provincesAvgAchievementDl;

    private Double districtAvgAchievementSx;

    private Double districtAvgAchievementYy;

    private Double districtAvgAchievementWl;

    private Double districtAvgAchievementLs;

    private Double districtAvgAchievementZz;

    private Double districtAvgAchievementHx;

    private Double districtAvgAchievementSw;

    private Double districtAvgAchievementDl;

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

    private Double provincesMinAchievementYw;

    private Double provincesMinAchievementSx;

    private Double provincesMinAchievementYy;

    private Double provincesMinAchievementWl;

    private Double provincesMinAchievementLs;

    private Double provincesMinAchievementZz;

    private Double provincesMinAchievementHx;

    private Double provincesMinAchievementSw;

    private Double provincesMinAchievementDl;

    private Double districtMinAchievementDl;

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

    private Long gradePersonYw;

    private Long gradePersonYy;

    private Long gradePersonWl;

    private Long gradePersonSx;

    private Long gradePersonLs;

    private Long gradePersonZz;

    private Long gradePersonHx;

    private Long gradePersonSw;

    /**
     * 录入时间
     */
    private LocalDateTime gmtCreate;

    private Long districtPersonYw;

    private Long districtPersonSx;

    private Long districtPersonYy;

    private Long districtPersonWl;

    private Long districtPersonLs;

    private Long districtPersonZz;

    private Long districtPersonHx;

    private Long districtPersonSw;

    private Long districtPersonDl;

    private Long provincesPersonYw;

    private Long provincesPersonSx;

    private Long provincesPersonYy;

    private Long provincesPersonWl;

    private Long provincesPersonLs;

    private Long provincesPersonZz;

    private Long provincesPersonHx;

    private Long provincesPersonSw;

    private Long provincesPersonDl;

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

    /**
     * 备注
     */
    private String remark;

    private Long gradePersonDl;

    private String name;

    private String schoolYears;

    private Double avgAchievementYw;

    private Double avgAchievementSx;

    private Double avgAchievementYy;

    private Double avgAchievementWl;

    private Double avgAchievementLs;

    private Double avgAchievementZz;

    private Double avgAchievementDl;

    private Double avgAchievementSw;

    private Double avgAchievementHx;

    private Double districtMinAchievement;

    private Double gradeMinAchievement;

    private Double provincesMinAchievement;

    private Double districtMaxAchievement;

    private Double gradeMaxAchievement;

    private Double provincesMaxAchievement;


}
