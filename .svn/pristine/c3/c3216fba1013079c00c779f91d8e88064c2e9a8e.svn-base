package com.benwunet.bks.config;

import com.benwunet.mws.model.exception.BaseException;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseResult handleUserNotExistException(BaseException e){
		return ResponseResult.app(ResultCode.PT_ERROR, e.getStatus(), e.getMsg(),"");
	}

	public static void main(String[] args) {
		try {
			List<String> list=new ArrayList<>(2);

		}catch (Exception e){

			System.out.println(e.getMessage());

		}finally {

			System.out.println("哈哈");
		}

	}

}


