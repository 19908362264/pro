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
@ApiModel(value = "教师请求参数",description = "教师新增/修改请求参数")
public class TimesAvg implements Serializable {

    private static final long serialVersionUID = 3725850398884767777L;

    @ApiModelProperty(value = "学校平均分")
    private List<DataVO> schoolAvg;

    @ApiModelProperty(value = "区县平均分")
    private List<DataVO> countyAvg;

    @ApiModelProperty(value = "全市平均分")
    private List<DataVO> cityAvg;

    public static TimesAvg of(){
        return new TimesAvg();
    }

}