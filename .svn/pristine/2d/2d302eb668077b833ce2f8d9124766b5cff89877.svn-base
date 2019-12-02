package com.benwunet.mws.gateway.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/9/9 9:17
 */

@Data
@ApiModel
public class loginUserVO implements Serializable {
   @ApiModelProperty(value = "用户名",notes = "用户名")
    private String mobile;
    @ApiModelProperty(value = "密码",notes = "密码")
    private String password;
    @ApiModelProperty(value = "验证码",notes = "验证码")
    private String code;
    @ApiModelProperty(value = "点击验证码后返回的key",notes = "点击验证码后返回的key")
    private String key;
    @ApiModelProperty(value = "用户类型0:学生或家长;1:专家",notes = "用户类型0:学生或家长;1:专家")
    private String type;
}
