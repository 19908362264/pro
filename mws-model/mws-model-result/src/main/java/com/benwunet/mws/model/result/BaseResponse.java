package com.benwunet.mws.model.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.bytebuddy.asm.Advice;


import java.io.Serializable;

/**
 * @param
 * @author zuoli
 * @return
 * @date 2019/9/4 19:01
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("返回类")
public class BaseResponse<T> implements Serializable {

    @ApiModelProperty(value = "返回码：正确0, 错误1")
    private int code;
    @ApiModelProperty(value = "返回消息")
    private String msg;
    @ApiModelProperty(value = "返回状态")
    private int status;
    @ApiModelProperty(value = "返回具体内容", position = 4)
    private T data;

    public BaseResponse(int code, String msg, int status, T data) {
        this.code = code;
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public BaseResponse() {

    }

    public static BaseResponse of() {

        return new BaseResponse();
    }


}
