package com.benwunet.bks.model.queryVO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;


/**
 * @author liushuangqing
 * @description 成绩查询QueryVO
 * @date 2019/11/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ScoreQueryVO extends BaseQueryVO {

    @NotBlank(message = "学校名称不能为空")
    @ApiModelProperty(value = "学校名称", required = true)
    private String schoolName;

    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    @ApiModelProperty("考号")
    private String studentId;
}
