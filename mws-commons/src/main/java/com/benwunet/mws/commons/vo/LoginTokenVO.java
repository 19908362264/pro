package com.benwunet.mws.commons.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@ApiModel(value = "登录 Token", description = "登录授权 refresh_token 以及刷新 refresh_token")
@NoArgsConstructor
@AllArgsConstructor
public class LoginTokenVO implements Serializable {

    @ApiModelProperty(value = "Token", example = "a36b7565-6112-4b18-b768-b844049d6ae3")
    @JsonProperty("access_token")
    private String accessToken;

    @ApiModelProperty(value = "token 类型", example = "bearer")
    @JsonProperty("token_type")
    private String tokenType;

    @ApiModelProperty(value = "token 过期刷新使用", example = "4b3597a0-1c35-4698-b31e-38391c1b6c86")
    @JsonProperty("refresh_token")
    private String refreshToken;

    @ApiModelProperty(value = "过期时间", example = "28799")
    @JsonProperty("expires_in")
    private int expiresIn;

    @ApiModelProperty(value = "授权范围", example = "app")
    private String scope;

}
