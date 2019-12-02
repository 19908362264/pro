package com.benwunet.bks.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zfy
 * @date 2019/8/27
 */
@Data
public class ProfessorAppointmentVO implements Serializable {

    private static final long serialVersionUID = -6945901144832894888L;

    private Integer id;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String headImg;

    /**
     * 服务类别
     */
    @ApiModelProperty(value = "服务类别", example = "1")
    private String serviceType;

    /**
     * 服务方式 1-图文 2-电话 3-视频
     */
    @ApiModelProperty(value = "服务方式 1-图文 2-电话 3-视频", example = "1")
    private String serviceWay;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额", example = "1")
    private String cost;

    /**
     * 问题描述
     */
    @ApiModelProperty(value = "问题描述", example = "很麻烦")
    private String description;

    /**
     * 状态 1-已预约，未支付 2-待咨询 3-进行中 4-已完成 5-已取消
     */
    @ApiModelProperty(value = "状态 1-已预约，未支付 2-待咨询 3-进行中 4-已完成 5-已取消", example = "很麻烦")
    private Integer status;

    @ApiModelProperty(value = "预约时间", example = "[{\"beginTime\":\"2016-08-04T10:09:30\",\"endTime\":\"2016-08-04T10:10:30\"}]")
    private List<BksProfessorTimeVO> list;



}
