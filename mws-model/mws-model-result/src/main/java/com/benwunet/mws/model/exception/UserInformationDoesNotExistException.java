package com.benwunet.mws.model.exception;

public class UserInformationDoesNotExistException extends BaseException {

    public UserInformationDoesNotExistException(){
        super(10406, "用户信息不存在");
    }

}
