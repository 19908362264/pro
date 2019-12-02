package com.benwunet.bks.entity.homepage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zfy
 * @date 2019/7/30
 */
@Data
@ApiModel(value = "学生成绩排名变化",description = "学生成绩排名变化，change正数为上升，负数为下降")
public class ScoreRanking implements Serializable {

    private static final long serialVersionUID = -3723172636903004765L;

    @ApiModelProperty(value = "排名")
    private String ranking;

    @ApiModelProperty(value = "姓名")
    private String studentName;

    @ApiModelProperty(value = "总分")
    private Double score;

    @ApiModelProperty(value = "排名变化")
    private String changes;


}