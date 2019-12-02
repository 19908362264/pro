package com.benwunet.mws.model.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: WangLin
 * @Date: 2019/7/9 16:09
 * @Description:  规定成功响应此结果
 **/
@Data
@ApiModel(value = "响应返回值", description = "统一返回此兑现")
@NoArgsConstructor
public class RequestResult<T> implements Serializable {

    public RequestResult(int code){
        this.code = code;
    }
    public RequestResult(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public RequestResult(int code, T data){
        this.code = code;
        this.data = data;
    }
    public RequestResult(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RequestResult(T data){
        this.code = 200;
        this.msg = "请求成功";
        this.data = data;
    }
    public RequestResult(String msg, T data){
        this.code = 200;
        this.msg = msg;
        this.data = data;
    }
    /**
     * @Auther: WangLin
     * @Date: 2019/7/9 16:12
     * @Description: 成功响应
     **/

    public static RequestResult ok(Object data){
        return new RequestResult(ResultCodeNew.SUCCESS.getCode(), ResultCodeNew.SUCCESS.getMessage(), data);
    }
    public static RequestResult ok(String msg, Object data){
        return new RequestResult(ResultCodeNew.SUCCESS.getCode(), msg, data);
    }
    /**
     * @Auther: WangLin
     * @Date: 2019/7/9 16:13
     * @Description: 请求失败
     **/

    public static RequestResult error(ResultCodeNew code){
        return new RequestResult(code.getCode(), code.getMessage(), "");
    }

    public static RequestResult error(int code, String msg){
        return new RequestResult(code, msg);
    }
    /**
     * @Auther: WangLin
     * @Date: 2019/7/9 16:04
     * @Description: 代表此次请求成与否 0：成功 1：失败
     **/

    @ApiModelProperty(value = "200：代表此次请求成与否, 其他请参考错误代码信息表", example = "200")
    private int code;

    /**
     * @Auther: WangLin
     * @Date: 2019/7/9 16:06
     * @Description: 成功或失败返回的消息
     **/
    @ApiModelProperty(value = "请求信息", example = "请求成功")
    private String msg;

    /**
     * @Auther: WangLin
     * @Date: 2019/7/9 16:06
     * @Description: 此次请求响应数据 T 类型
     **/
    @ApiModelProperty(position = 2, value = "响应数据")
    private T data;
}
