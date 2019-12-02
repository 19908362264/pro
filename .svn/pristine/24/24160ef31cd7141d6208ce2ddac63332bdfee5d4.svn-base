package com.benwunet.bks.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author liushuangqing
 * @since 2019-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EnrollmentBatchControl implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 流水号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 招生年份
     */
    private String enrollmentYear;

    /**
     * 招生批次
     */
    private String enrollmentBatch;

    /**
     * 控制分数
     */
    private BigDecimal enrollmentControlScore;
    /**
     * 文理科
     */
    private String admitted;
    /**
     * 省市id
     */
    private String provinceId;
}
