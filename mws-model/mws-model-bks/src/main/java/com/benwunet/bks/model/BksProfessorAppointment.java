package com.benwunet.bks.model;


import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
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
public class BksProfessorAppointment implements Serializable {

private static final long serialVersionUID=1L;


    private Integer id;

    /**
     * 专家id
     */
    @ApiModelProperty(value = "专家id", example = "123")
    private String professorId;

    private String professorName;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "提问者id", example = "123")
    private String userId;
    private String userName;

    /**
     * 服务类别
     */
    @ApiModelProperty(value = "服务类别", example = "1")
    private String serviceType;

    /**
     * 服务方式 
     */
    @ApiModelProperty(value = "服务方式", example = "1")
    private String serviceWay;

    /**
     * 1-图文 2-电话 3-视频
     */
    @ApiModelProperty(value = "咨询方式", example = "1")
    private String cost;

    /**
     * 问题描述
     */
    @ApiModelProperty(value = "问题描述", example = "很麻烦")
    private String description;

    /**
     * 状态 1-已预约，未支付 2-待咨询 3-进行中 4-已完成 5-已取消
     */

    private Integer status;

//    /**
//     * 预约开始时间
//     */
//    @ApiModelProperty(value = "预约开始时间", example = "2016-08-04T10:11:30")
//    private LocalDateTime beginTime;
//
//    /**
//     * 预约结束时间
//     */
//    @ApiModelProperty(value = "预约结束时间", example = "2017-08-04T10:11:30")
//    private LocalDateTime endTime;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String remark;


}
