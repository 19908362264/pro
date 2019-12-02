package com.benwunet.bks.controller.base;

import com.benwunet.mws.model.result.ResponseResult;

import java.util.List;

/**
 * BaseController
 * @author zhoux
 * @date 2019/11/15
 */
public class BaseController {
    /**
     * 返回成功信息
     */
    protected ResponseResult success(){
        return new ResponseResult(0, "OK", 0, null);
    }
    /**
     * 返回成功信息
     */
    protected ResponseResult success(Integer total, List list){
        return new ResponseResult(0, "OK", total, list);
    }
    /**
     * 返回成功信息
     */
    protected ResponseResult success(Object object){
        return new ResponseResult(0, "OK", 1, object);
    }
    /**
     * 返回失败信息
     */
    protected ResponseResult fail(String msg){
        return new ResponseResult(1, msg, 0, null);
    }
}
