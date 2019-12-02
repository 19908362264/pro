package com.benwunet.bks.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@ApiModel(value = "提问请求参数", description = "提问请求参数")
public class BksQuestionDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "用户id（提问者id）")
    private String userId;

    @ApiModelProperty(value = "用户（提问者）")
    private String userName;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "分类id")
    private Integer typeId;

    @ApiModelProperty(value = "分类名称")
    private String typeName;

    @ApiModelProperty(value = "提问方式 1-悬赏  2-专家  3-免费")
    private Integer manner;

    @ApiModelProperty(value = "专家id，当提问方式为专家时有值")
    private String professorId;

    @ApiModelProperty(value = "悬赏金额，当提问方式为专家或者悬赏时有值")
    private Double money;

    @ApiModelProperty(value = "问题浏览次数")
    private Long num;

    @ApiModelProperty(value = "状态 0-未解决 1-已解决  2-进行中（指定给专家时，专家有回答，但是未被采纳）")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "图片地址")
    private List<String> picUrls;

}
