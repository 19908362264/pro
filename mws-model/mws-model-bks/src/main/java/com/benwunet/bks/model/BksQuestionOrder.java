package com.benwunet.bks.model;


import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zuoli
 * @since 2019-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksQuestionOrder implements Serializable {

private static final long serialVersionUID=1L;


    private Integer id;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 问题id
     */
    private String appointmentId;
    private String questionId;


    /**
     * 支付金额
     */
    private String actualAmount;

    /**
     * 状态 0-未支付 1-已支付 2-已过期 3-已取消 4-发起退款 5-退款完成
     */
    private Integer status;
    //提问方式 1-悬赏  2-专家  3-指定专家
    private Integer type;
    private LocalDateTime payTime;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String remark;


}
