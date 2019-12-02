package com.benwunet.bks.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author zfy
 * @date 2019/8/30
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "专家可预约时间", description = "专家可预约时间")
public class WorkVO implements Serializable {

    private static final long serialVersionUID = 6002872431440570603L;
    @ApiModelProperty(value = "预约时间段")
    private String date;

    @ApiModelProperty(value= "0:未预约 1：已预约 3:待办 4：已办 5：进行中 6：失效")
    private Integer status;

    @ApiModelProperty(value = "状态列表")
    private List<StatusVO> statusList;

    public static WorkVO of(){

        return new WorkVO();
    }
}
