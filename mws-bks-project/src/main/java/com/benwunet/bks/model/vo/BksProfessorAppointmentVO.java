package com.benwunet.bks.model.vo;

import com.benwunet.bks.model.BksProfessor;
import com.benwunet.bks.model.BksProfessorTime;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/8/23 8:43
 */
@Data
public class BksProfessorAppointmentVO {
    private Integer id;




    /**
     * 专家id
     */
    @ApiModelProperty(value = "专家id", example = "001")
    private String professorId;
    private String professorName;




    /**
     * 用户id
     */
    @ApiModelProperty(value = "提问者id", example = "123")
    private String userId;
    @ApiModelProperty(value = "提问者名称", example = "123")
    private String userName;




    /**
     * 服务类别
     */
    @ApiModelProperty(value = "服务类别", example = "1")
    private String serviceType;

    /**
     * 服务方式1-图文 2-电话 3-视频
     */
    @ApiModelProperty(value = "服务方式", example = "1")
    private String serviceWay;


    @ApiModelProperty(value = "收费", example = "1000")
    private String cost;

    /**
     * 问题描述
     */
    @ApiModelProperty(value = "问题描述", example = "很麻烦")
    private String description;


    private String date;

    /**
     * 状态 1-已预约，未支付 2-待咨询 3-进行中 4-已完成 5-已取消
     */

    private Boolean status;

    @ApiModelProperty(value = "专家id", example = "[{\"data\":\"2019-09-06\",\"type\":1,\"timeType\":1},{\"data\":\"2019-09-06\",\"type\":1,\"timeType\":2}]")
    private List<BksProfessorTimeVO> list;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String remark;

}
