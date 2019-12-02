package com.benwunet.mws.model.result;

import java.io.Serializable;

/**
 *  @author WangLin
 *  @Date 2019/4/28 10:55
 */
public interface ResultCode extends Serializable {
    int PT_OK = 0;
    int PT_ERROR = 1;
    int SC_OK = 200;
    int SC_BAD_REQUEST = 400;
    int SC_UNAUTHORIZED = 401;
    int SC_NOT_FOUND = 404;
    int SC_INTERNAL_SERVER_ERROR = 500;
    int SC_NOT_IMPLEMENTED = 501;
}
