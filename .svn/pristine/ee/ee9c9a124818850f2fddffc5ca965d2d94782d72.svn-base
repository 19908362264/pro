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
 * 服务类别表
 * </p>
 *
 * @author zhoux
 * @since 2019-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksMemberCardServiceType implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 服务类别名称（如：专家咨询、专家一对一）
     */
    private String name;

    /**
     * 服务方式（如：0，1，2）（0：图文咨询；1：电话咨询；2：音视频咨询）
     */
    private String method;

    /**
     * 价格
     */
    private BigDecimal price;

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
