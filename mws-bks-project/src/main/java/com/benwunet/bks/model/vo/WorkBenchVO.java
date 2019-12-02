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
@ApiModel(value = "专家工作台", description = "专家工作台")
public class WorkBenchVO implements Serializable {

    private static final long serialVersionUID = 5322313251450351654L;

    @ApiModelProperty(value = "待处理工作数")
    private Integer backlogNum;

    @ApiModelProperty(value = "工作列表")
    private List<WorksVO> workList;

    public static WorkBenchVO of(){
        return new WorkBenchVO();
    }
}
