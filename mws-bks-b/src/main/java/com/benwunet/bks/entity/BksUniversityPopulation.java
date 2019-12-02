package com.benwunet.bks.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 高校人气值
 * </p>
 *
 * @author C
 * @since 2019-06-25
 */
@Data

public class BksUniversityPopulation implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 大学ID
     */
    private String universityId;

    /**
     * 大学名称
     */
    private String universityName;

    /**
     * 人气值
     */
    private Long populationValue;

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


}
