package com.benwunet.mws.model.exception;

public class LoginHasExpiredException extends BaseException {

    public LoginHasExpiredException(){
        super(10405, "登录已经失效");
    }

}
