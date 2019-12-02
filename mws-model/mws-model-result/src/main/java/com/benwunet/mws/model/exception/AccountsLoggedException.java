package com.benwunet.mws.model.exception;

public class AccountsLoggedException extends BaseException {

    public AccountsLoggedException(){
        super(10404, "账号在其它手机登录");
    }

}
