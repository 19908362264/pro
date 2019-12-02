package com.benwunet.mws.model.exception;

public class UserloginpasswordErrorException extends BaseException {

    public UserloginpasswordErrorException(){
        super(10408, "用户登录密码错误");
    }

}
