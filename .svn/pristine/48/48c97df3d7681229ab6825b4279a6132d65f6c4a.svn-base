package com.benwunet.mws.gateway.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.benwunet.mws.model.exception.BaseException;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.netflix.client.ClientException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alibaba.fastjson.JSONObject;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

/**
 * 异常处理逻辑
 * @author xiangkaihong
 * @date 2019/5/4 10:17
 */

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseResult handleUserNotExistException(BaseException e){
		System.out.println("---------------------------------->");
		e.printStackTrace();
		return ResponseResult.app(ResultCode.PT_ERROR, e.getStatus(), e.getMsg(),"");
	}

}
