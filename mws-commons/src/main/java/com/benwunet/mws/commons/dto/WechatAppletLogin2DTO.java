package com.benwunet.mws.commons.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value="微信小程序登录数据", description="微信小程序登录数据")
public class WechatAppletLogin2DTO implements Serializable {

    @NotNull(message = "code 授权码必填*")
    @ApiModelProperty(required = true, value = "微信小程序临时授权码", name = "code", example = "033YGyIQ11w8d21GRgIQ1x0iIQ1YGyIL")
    private String code;

    @NotNull(message = "data 加密数据必填*")
    @ApiModelProperty(required = true, value = "微信小程序包括敏感数据在内的完整用户信息的加密数据", name = "data", example = "SY5GaogjfuGkL2b5VJVleV7dygYWmEfxnNIQjVBjL00Wy8dkpfcDzTVlj32SqjL8qKQkDLMoECCwUDdsoG8EaydYJrTqCocnLVSp4SGEg6SiMWchRPWqePSX4KS9qPRaf8yZZso16B4PkQ1lmh4ayQvs6m46gGgNNLYjUc6QlpiwMBH2H5EWwvxecN3wicwD8eCIm8GLFnrQ/myNVe12FnhBZTMSSQIPWrrkdbhH1HUmLeJCnXJul/ggg790Fq9+StMQJuWmhITnj42irixcz70ao+Zic0ZYpkBNjRhSzeOeldp24988iwLIub8wsRHnY9od/zXsv96kYz0zXUVct2FK8iu9eOCC8V0qiMbLrN1HUre/GUE3BOhhB9GEff6OV9KNo2L5ZYdFXSBIe/ClUEb4DBeuXobHQ6MvmN3lkQ9eNwPT24YwkucHTAMjHozFwB7bSaDe2yna9usIU/K7wp3XgLoj8QooI6m2eLZep7I=")
    private String data;

    @NotNull(message = "iv 数据加密方式必填*")
    @ApiModelProperty(required = true, value = "微信小程序加密算法的初始向量", name = "iv", example = "VgSUZiHN0gdKmsZweQUhOg==")
    private String iv;

    @NotNull(message = "userId 手机必填*")
    @ApiModelProperty(required = true, value = "用户唯一标识", name = "userId", example = "000000")
    private String userId;

    @NotNull(message = "distinction 登录标识必填*")
    @ApiModelProperty(required = true, value = "登录客户端【system：PC运营端，member：会员端】", name = "distinction", example = "member")
    private String distinction;
}
