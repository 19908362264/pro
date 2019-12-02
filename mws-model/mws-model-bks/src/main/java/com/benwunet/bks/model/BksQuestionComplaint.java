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
 * @since 2019-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksQuestionComplaint implements Serializable {

private static final long serialVersionUID=1L;


    private Integer id;

    /**
     * 被投诉者id
     */
    private String userId;

    /**
     * 业务id
     */
    private Integer businessId;

    /**
     * 业务类型 1-问题 2-答案
     */
    private Integer businessType;

    /**
     * 投诉内容
     */
    private String complaint;

    /**
     * 类别 1-仿冒他人 2-政治谣言  3-淫秽色情  4-血腥暴力  5-违法违禁品 6-其他 7-反馈意见
     */
    private Integer type;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String operatorId;

    private String operatorName;

    private String remark;


}
