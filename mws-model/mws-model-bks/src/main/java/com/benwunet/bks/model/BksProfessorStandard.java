package com.benwunet.bks.model;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zuoli
 * @since 2019-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class BksProfessorStandard implements Serializable {

private static final long serialVersionUID=1L;


    private Integer id;

    /**
     * 专家id
     */
    @ApiModelProperty(value = "专家Id")
    private String professorId;

    /**
     * 1-图文 2-电话 3-视频 4-指定专家
     */
    @ApiModelProperty(value = "服务方式1-图文 2-电话 3-视频 4-指定专家")
    private String type;

    /**
     * 费用 
     */
    @ApiModelProperty(value = "服务费用")
    private Double typeCost;

    private LocalDateTime gmtCreate;

    private String remak;


}
