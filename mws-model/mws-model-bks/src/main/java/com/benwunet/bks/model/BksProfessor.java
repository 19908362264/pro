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
 * @author C
 * @since 2019-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BksProfessor implements Serializable {

private static final long serialVersionUID=1L;


    private Integer id;

    /**
     * 关联bks_user
     */
    private String userId;

    /**
     * 名称
     */
    private String professorName;
    private String mobile;

    private String headImg;

    /**
     * 类型 1-特约专家 2-首席专家  3-明星专家 4-金牌专家
     */
    private Integer professorType;

    /**
     * 擅长领域
     */
    private String skilledField;

    /**
     * 背景简介
     */
    private String introduction;

    /**
     * 指导年数
     */
    private Integer guideYear;

//    /**
//     * 服务类别，多个类别用逗号“，”分割
//     */
//    private String serviceType;

    /**
     * 计费标准
     */
    private String chargingStandard;

    /**
     * 回答被采纳数
     */
    private Integer acceptNum;

    /**
     * 状态 0-未认证 1-已认证
     */
    private String status;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer operatorId;

    private String operatorName;

    private String remark;


}
