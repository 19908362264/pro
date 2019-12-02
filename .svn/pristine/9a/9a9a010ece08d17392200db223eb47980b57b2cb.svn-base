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
public class WorksVO implements Serializable {

    private static final long serialVersionUID = 6002872431440570603L;

    @ApiModelProperty(value = "日期")
    private String date;

    @ApiModelProperty(value = "状态列表")
    private List<StatusVO> statusList;

    public static WorksVO of(){
        return new WorksVO();
    }
}
