package com.benwunet.mws.model.exception;


public class BaseException extends RuntimeException {

    private Integer status;

    private String msg;

    public BaseException(){}
    public BaseException(Integer status, String msg){
        this.status = status;
        this.msg = msg;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
