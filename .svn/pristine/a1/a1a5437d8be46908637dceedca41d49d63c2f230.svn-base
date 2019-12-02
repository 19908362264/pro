package com.benwunet.bks.entity.homepage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zfy
 * @date 2019/7/30
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "数据类",description = "返回数据名以及数据值")
public class DataVO implements Serializable {

    private static final long serialVersionUID = 1369743984688354807L;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("值")
    private String num;

    public static DataVO of(){
        return new DataVO();
    }

}