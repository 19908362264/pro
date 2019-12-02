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
 * 成绩名次
 * </p>
 *
 * @author liushuangqing
 * @since 2019-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksExamRanking implements Serializable {

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

    private String name;

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
     * 学级
     */
    private String schoolYears;

    /**
     * 考试批次
     */
    private String examBatches;

    /**
     * 年级名次
     */
    private Double gradeRanking;

    /**
     * 省市区名次
     */
    private Double provincesRanking;

    /**
     * 区县名次
     */
    private Double districtRanking;

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

    /**
     * 备注
     */
    private String remark;

    /**
     * 年级排名
     */
    private Double gradeRankingYw;

    private Double gradeRankingSx;

    private Double gradeRankingYy;

    private Double gradeRankingWl;

    private Double gradeRankingLs;

    private Double gradeRankingZz;

    private Double gradeRankingHx;

    private Double gradeRankingSw;

    private Double gradeRankingDl;

    /**
     * 区排名
     */
    private Double districtRankingYw;

    private Double districtRankingSx;

    private Double districtRankingYy;

    private Double districtRankingWl;

    private Double districtRankingLs;

    private Double districtRankingZz;

    private Double districtRankingHx;

    private Double districtRankingSw;

    private Double districtRankingDl;

    /**
     * 省排名
     */
    private Double provincesRankingYw;

    private Double provincesRankingSx;

    private Double provincesRankingYy;

    private Double provincesRankingWl;

    private Double provincesRankingLs;

    private Double provincesRankingZz;

    private Double provincesRankingHx;

    private Double provincesRankingSw;

    private Double provincesRankingDl;

    /**
     * 升降
     */
    private Integer gradeRankingYwChg;

    private Integer gradeRankingSxChg;

    private Integer gradeRankingYyChg;

    private Integer gradeRankingWlChg;

    private Integer gradeRankingLsChg;

    private Integer gradeRankingDlChg;

    private Integer gradeRankingSwChg;

    private Integer gradeRankingZzChg;

    private Integer gradeRankingHxChg;

    /**
     * 总成绩年级排名变化
     */
    private Integer gradeRankingChg;

    /**
     * 总分
     */
    private Double myScore;

    /**
     * 状态 0-作废，1-正常
     */
    private Boolean status;


}
