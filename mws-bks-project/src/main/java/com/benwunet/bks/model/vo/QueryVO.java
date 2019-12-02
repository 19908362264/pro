package com.benwunet.bks.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author zfy
 * @date 2019/8/21
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "请求参数111", description = "公共查询请求参数类111")
public class QueryVO implements Serializable {

    private static final long serialVersionUID = 6044223419355965308L;

    @ApiModelProperty(value = "公共请求参数，即关键字搜索")
    private String param;

    @ApiModelProperty(value = "当前页码", example = "1")
    private Long pageCurrent;

    @ApiModelProperty(value = "每页数量", example = "10")
    private Long pageSize;

    @ApiModelProperty(value = "分类")
    private List<Integer> types;

    @ApiModelProperty(value = "问题状态,0-未解决 1-已解决")
    private Integer questionStatus;

    @ApiModelProperty(value = "支付状态, 0-未支付 1-已支付 2-已过期 3-已取消 4-发起退款 5-退款完成")
    private Integer payStatus;

    @ApiModelProperty(value = "问题类别")
    private Integer typeId;

    @ApiModelProperty(value = "排序字段，‘TIME’按时间")
    private String sort;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "日期")
    private String date;

    @ApiModelProperty(value = "日期列表")
    private List<String> dates;

    @ApiModelProperty(value = "预约状态 1-未支付 2-待咨询 3-进行中 4-已完成 5-已取消")
    private Integer status;

    @ApiModelProperty(value = "答案id")
    private Integer answerId;

    @ApiModelProperty(value = "问题id")
    private Integer questionId;

    @ApiModelProperty(value = "开始时间")
    private String beginTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "提问方式 1-悬赏  2-专家  3-免费 4-指定专家")
    private List<Integer> manners;

}
