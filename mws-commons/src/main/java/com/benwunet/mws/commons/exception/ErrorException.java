package com.benwunet.mws.commons.exception;

import com.benwunet.mws.commons.enums.ResultCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: WangLin
 * @Date: 2019/7/9 17:21
 * @Description:
 **/
@Getter
@NoArgsConstructor
public class ErrorException extends Exception implements Serializable {
    private static final long serialVersionUID = 5481596588955034193L;
    /**
     * @Description: 响应码
     **/
    private Integer code;
    /**
     * @Description: 错误响应信息
     **/
    private String message;
    /**
     * @Description: 覆盖无参构造
     **/
    public ErrorException(ResultCode error){
        this.code = error.getCode();
        this.message = error.getMessage();
    }
    /**
     * @Description: 覆盖无参构造
     **/
    public ErrorException(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public ErrorException (String msg) {
        this.message = message;
    }
}
