package com.benwunet.bks.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

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
@ApiModel(value = "回答问题请求参数", description = "回答问题请求参数")
public class BksQuestionAnswerDTO implements Serializable {

private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "问题id")
    private Integer questionId;

    @ApiModelProperty(value = "回答内容")
    private String answerContent;

    @ApiModelProperty(value = "回答者id")
    private String userId;

    @ApiModelProperty(value = "回答者姓名")
    private String userName;

    @ApiModelProperty(value = "答案类别 1-专家答案 2-普通答案")
    private Integer type;

    @ApiModelProperty(value = "是否被采纳 0-否 1-是")
    private Integer acceptType;

    @ApiModelProperty(value = "点赞数量")
    private Long num;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "图片地址")
    private List<String> picUrls;


}
