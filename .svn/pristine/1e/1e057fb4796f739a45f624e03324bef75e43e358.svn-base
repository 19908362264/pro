package com.benwunet.bks.config;

import com.benwunet.mws.model.exception.LoginHasExpiredException;
import com.benwunet.mws.model.result.ResponseResult;
import com.benwunet.mws.model.result.ResultCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    /**
     *<p><b>自定义错误处理的Controller类</b></p>
     * @Description 自定义错误处理的Controller类</ p>
     */
    @RequestMapping(value = "/error/{code}")
    public ResponseResult error(@PathVariable int code) {
        String msg = "";
        switch (code) {
            case 401:
                throw new LoginHasExpiredException();
            case 403:
                msg = "参数类型不匹配";
                break;
            case 404:
                msg = "访问资源不存在";
                break;
            case 405:
                msg = "请求方式不正确";
                break;
            case 500:
                msg = "内部服务出错";
                break;
        }
        return ResponseResult.app(ResultCode.PT_ERROR,  code, msg, "");
    }

}
