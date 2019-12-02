package com.benwunet.mws.commons.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value="微信小程序登录数据", description="微信小程序登录数据")
public class WechatAppletLoginReg2DTO implements Serializable {

    @NotNull(message = "code 授权码必填*")
    @ApiModelProperty(required = true, value = "微信小程序临时授权码", name = "code", example = "033YGyIQ11w8d21GRgIQ1x0iIQ1YGyIL")
    private String code;

    @NotNull(message = "userId 手机必填*")
    @ApiModelProperty(required = true, value = "用户唯一标识", name = "userId", example = "000000")
    private String userId;

    @NotNull(message = "distinction 登录标识必填*")
    @ApiModelProperty(required = true, value = "登录客户端【system：PC运营端，member：会员端】", name = "distinction", example = "member")
    private String distinction;
}
