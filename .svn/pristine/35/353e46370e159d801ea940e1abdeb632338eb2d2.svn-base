package com.benwunet.bks.entity.homepage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author zfy
 * @date 2019/7/30
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "首页返回数据",description = "首页返回数据")
public class HomeData implements Serializable {

    private static final long serialVersionUID = -5900256773849039026L;

    @ApiModelProperty(value = "参考人数")
    private String referenceNum;

    @ApiModelProperty(value = "年级平均分")
    private String gradeAvg;

    @ApiModelProperty(value = "学级")
    private String schoolYear;

    @ApiModelProperty(value = "考试批次")
    private String examBatch;

    @ApiModelProperty(value = "历次平均成绩")
    private TimesAvg timesAvg;

    @ApiModelProperty(value = "总分数段人数")
    private List<DataVO> scoreRange;

    @ApiModelProperty(value = "成绩排名")
    private List<ScoreRanking> scoreRanking;

    @ApiModelProperty(value = "各科成绩分析")
    private SubjectAnalyze subjectAnalyze;

    public static HomeData of(){
        return new  HomeData();
    }


}