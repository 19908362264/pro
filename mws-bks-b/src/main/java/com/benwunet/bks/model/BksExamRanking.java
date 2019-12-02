package com.benwunet.bks.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 成绩名次
 * </p>
 *
 * @author C
 * @since 2019-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksExamRanking implements Serializable {

    private static final long serialVersionUID = 1650646105962294153L;

//private static final long serialVersionUID=1L;

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
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

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

    private Double gradeRankingYw;

    private Double gradeRankingSx;

    private Double gradeRankingYy;

    private Double gradeRankingWl;

    private Double gradeRankingLs;

    private Double gradeRankingZz;

    private Double gradeRankingHx;

    private Double gradeRankingSw;

    private Double gradeRankingDl;

    private Double districtRankingYw;

    private Double districtRankingSx;

    private Double districtRankingYy;

    private Double districtRankingWl;

    private Double districtRankingLs;

    private Double districtRankingZz;

    private Double districtRankingHx;

    private Double districtRankingSw;

    private Double districtRankingDl;

    private Double provincesRankingYw;

    private Double provincesRankingSx;

    private Double provincesRankingYy;

    private Double provincesRankingWl;

    private Double provincesRankingLs;

    private Double provincesRankingZz;

    private Double provincesRankingHx;

    private Double provincesRankingSw;

    private Double provincesRankingDl;
    private Double gradeRankingYwChg;
    private Double gradeRankingSxChg;
    private Double gradeRankingYyChg;
    private Double gradeRankingSwChg;
    private Double gradeRankingHxChg;
    private Double gradeRankingLsChg;
    private Double gradeRankingDlChg;
    private Double gradeRankingWlChg;
    private Double gradeRankingZzChg;
    private Double gradeRankingChg;

    private Double myScore;


}
