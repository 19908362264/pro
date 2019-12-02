package com.benwunet.mws.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询参数类
 *
 * @author zfy
 * @date 2019/7/26
 */
@Data
@ApiModel(value = "请求参数",description = "公共查询请求参数类")
public class QueryVO implements Serializable {

    private static final long serialVersionUID = -3339508250416468804L;

    /**
     * 公共请求参数
     */
    @ApiModelProperty(value = "公共请求参数，即关键字搜索")
    private String param;

    /**
     * 主键ID列表
     */
    @ApiModelProperty(value = "主键ID列表")
    private List<Integer> ids;

    /**
     * 用户ID列表
     */
    @ApiModelProperty(value = "用户ID列表")
    private List<String> userIds;

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码",example = "1")
    private Long pageCurrent;

    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量",example = "10")
    private Long pageSize;

    /**
     * 学级
     */
    @ApiModelProperty("学级")
    private String schoolYears;

    /**
     * 考试批次
     */
    @ApiModelProperty("考试批次")
    private String examName;

    /**
     * 学科
     */
    @ApiModelProperty("学科")
    private List<String> subjects;

    /**
     * 学校名称
     */
    @ApiModelProperty("学校名称")
    private String schoolName;

    /**
     * 分段类别
     */
    @ApiModelProperty("分段类别")
    private String sectionType;

}
