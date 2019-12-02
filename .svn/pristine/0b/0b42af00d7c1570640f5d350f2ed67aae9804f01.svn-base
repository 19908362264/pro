package com.benwunet.bks.config;

import com.benwunet.mws.model.exception.BaseException;
import com.benwunet.mws.model.result.RequestResult;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import com.benwunet.mws.model.result.ResultCodeNew;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseResult handleUserNotExistException(BaseException e){
		return ResponseResult.app(ResultCode.PT_ERROR, e.getStatus(), e.getMsg(),"");
	}
	/**
	 * @author WangLin
	 * @date 2019/10/25 14:14
	 * @description  这里处理@RequestBody ，验证不通过抛出的异常
	 **/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public RequestResult<String> validationErrorHandler(MethodArgumentNotValidException ex) {
		List<String> errorInformation = ex.getBindingResult().getAllErrors()
				.stream()
				.map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList());
		return RequestResult.error(ResultCodeNew.SYNTAX_ERROR.getCode(), errorInformation.get(0));
	}


}


