package com.benwunet.bks.model;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员卡券表
 * </p>
 *
 * @author zhoux
 * @since 2019-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksMemberCard implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 卡卷名称
     */
    private String name;

    /**
     * 使用状态（1：未使用；0：已使用）
     */
    private Boolean useStatus;

    /**
     * 状态（1：有效；0：无效）
     */
    private Boolean status;

    /**
     * 服务类别id

     */
    private Integer serviceTypeId;

    /**
     * 会员id
     */
    private Integer memberId;

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
