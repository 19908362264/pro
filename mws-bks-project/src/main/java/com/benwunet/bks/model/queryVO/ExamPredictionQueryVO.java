package com.benwunet.bks.model.queryVO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author liushuangqing
 * @description 高考预测QueryVO
 * @date 2019/11/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ExamPredictionQueryVO extends BaseQueryVO{
    /**
     * 分数
     */
    private Double score;
    /**
     * 文理科
     */
    private String admitted;
    /**
     * 省市id
     */
    private String provinceId;
    /**
     * 考试批次
     */
    private String batch;
    /**
     * 专业类
     */
    private String majorCategories;
    /**
     * 1,肯定；2否定
     */
    private Integer f211;

    /**
     * 1,肯定；2否定
     */
    private Integer f985;

    /**
     * 1,肯定；2否定
     */
    private Integer department;

    /**
     * 1,肯定；2否定
     */
    private Integer admissions;

    /**
     * 1,肯定；2否定
     */
    private Integer central;

    /**
     * 1,肯定；2否定
     */
    private Integer isSeal;
}
