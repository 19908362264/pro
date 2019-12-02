package com.benwunet.bks.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author zhoux
 * @since 2019-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksMember implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会员名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 开通时间
     */
    private LocalDateTime openingTime;

    /**
     * 有效期（如：12个月）
     */
    private Integer validTime;

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
