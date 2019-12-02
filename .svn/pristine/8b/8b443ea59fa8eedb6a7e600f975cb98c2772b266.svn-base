package com.benwunet.mws.commons.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: WangLin
 * @Date: 2019/7/9 17:21
 * @Description:
 **/
@Data
public class RuntimeBusinessException extends RuntimeException implements Serializable {

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
    public RuntimeBusinessException(int code, String message){
        this.code = code;
        this.message = message;
    }

}
