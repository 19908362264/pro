package com.benwunet.mws.commons.exception;

import com.benwunet.mws.commons.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: WangLin
 * @Date: 2019/7/9 17:21
 * @Description:
 **/
@Data
public class BusinessException extends ErrorException implements Serializable {

    /**
     * @Description: 覆盖无参构造
     **/
    public BusinessException(){
        super(ResultCode.EXECUTING_REQUEST_ERROR);
    }

    /**
     * @Description: 覆盖无参构造
     **/
    public BusinessException(ResultCode code){
        super(code);
    }

    /**
     * @Description: 覆盖无参构造
     **/
    public BusinessException(int code, String message){
        super(code, message);
    }

}
