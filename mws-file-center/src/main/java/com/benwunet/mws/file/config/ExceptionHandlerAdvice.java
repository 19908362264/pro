package com.benwunet.mws.file.config;

import java.util.HashMap;
import java.util.Map;

import com.benwunet.mws.model.exception.BaseException;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理逻辑
 * @author xiangkaihong
 * @date 2019/5/4 8:49
 */

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseResult handleUserNotExistException(BaseException e){
		return ResponseResult.app(ResultCode.PT_ERROR, e.getStatus(), e.getMsg(),"");
	}
}
