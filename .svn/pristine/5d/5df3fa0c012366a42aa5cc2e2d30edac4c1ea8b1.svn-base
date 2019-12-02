package com.benwunet.bks.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zfy
 * @date 2019/9/5
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "专家工作台状态", description = "专家工作台状态")
public class StatusVO implements Serializable {

    private static final long serialVersionUID = 1145293813216961765L;

    @ApiModelProperty(value = "状态数量")
    private Integer num;

    @ApiModelProperty(value = "状态 0:未预约 1：已预约 3:待办 4：已办 5：进行中 6：失效")
    private Integer status;
}
