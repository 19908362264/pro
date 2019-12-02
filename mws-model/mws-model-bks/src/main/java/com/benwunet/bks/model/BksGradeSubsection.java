package com.benwunet.bks.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zfy
 * @date 2019/7/30
 * <p>
 * 分段统计
 */
@Data
public class BksGradeSubsection implements Serializable {

    private static final long serialVersionUID = 9220414437873391256L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 分段名称
     */
    private String sectionType;

    /**
     * 学级
     */
    private String schoolYears;

    /**
     * 考试批次
     */
    private String examName;

    /**
     * 学科
     */
    private String subject;

    /**
     * 分段分数
     */
    private String sectionScore;

    /**
     * 分段人数
     */
    private Integer sectionNums;

    /**
     * 分段累计人数
     */
    private Integer sectionAccumulation;

    /**
     * 学校名称
     */
    private String schoolName;

}
