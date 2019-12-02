package com.benwunet.mws.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 *  @author WangLin
 *  @Date 2019/5/6 18:12
 */
@Data
public class ResponseResult extends HashMap<String, Object> implements Serializable {

    public ResponseResult(){}

    public static ResponseResult page(Integer code, String msg, Long count, Object data){
        ResponseResult responseResult = new ResponseResult ();
        responseResult.put ("code", code);
        responseResult.put ("msg", msg);
        responseResult.put ("count", count);
        responseResult.put ("data", data);
        return responseResult;
    }
    public static ResponseResult app(Integer status, Integer errorCode, String msg, Object data){
        ResponseResult responseResult = new ResponseResult ();
        responseResult.put ("status", status);
        responseResult.put ("code", errorCode);
        responseResult.put ("msg", msg);
        responseResult.put ("data", data);
        return responseResult;
    }

    public ResponseResult(int status, String msg, Object data){
        put ("data", data);
        put ("msg", msg);
        put ("status", status);
    }
    public ResponseResult(int status, String msg, int errorCode){
        put ("error_code", errorCode);
        put ("msg", msg);
        put ("status", status);
    }

    public ResponseResult(int status, int errorCode, String msg, Object data){
        put ("status", status);
        put ("error_code", errorCode);
        put ("msg", msg);
        put ("data", data);
    }

    /**
     *  layui 表格数据回显格式
     * @param code
     * @param msg
     * @param count
     * @param data
     */
    public ResponseResult(int code, String msg, Integer count, Object data){
        put ("code", code);
        put ("msg", msg);
        put ("count", count);
        put ("data", data);
    }
}
