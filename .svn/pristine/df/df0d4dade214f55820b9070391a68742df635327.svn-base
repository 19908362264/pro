package com.benwunet.mws.commons.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * 程序错误响应码
 * @author  WangLin
 * @date 2019/7/9 17:06
 */
@Getter
public enum ResultCode {
    /**
     *
     * @author FC
     * @date 2019/8/6 19:09
     */
    SUCCESS(200,"请求成功"),

    // 100   系统
    SYNTAX_ERROR(100400,"请求参数不匹配"),
    UNAUTHENTICATED(100401,"登录已经失效"),
    ACCESS_DENIED(100403,"服务器拒绝访问"),
    NOT_EXIST404(404,"请求资源不存在"),
    NOT_EXIST400(400,"请求参数类型匹配错误"),
    NOT_EXIST405(100405,"请求参数类型不匹配"),
    NOT_EXIST(100404,"请求资源不存在"),
    REQUEST_MODE_INCORRECT(100405,"请求方式不正确"),
    EXECUTING_REQUEST_ERROR(100500, "服务无响应"),
    SERVER_OVERLOAD_OR_MAINTENANCE(100503,"服务器超负载或停机维护"),
    FEIGN_ERROR(101503,"服务降级通知"),

    // 30   业务逻辑错误
    // 03 门店 04 会员 05 员工  06 支付 07 订单 08商品
    NULL_POINTER_EXCEPTION(300000,"空指针"),
    NO_MORE(999999,"没有更多了"),

    // 400   数据库错误
    DATA_DOES_NOT_FOUND(400001,"未查询到数据"),
    DATABASE_OPERATION_ERROR(400002,"操作失败")
    ;


    /**成员变量*/
    private int code;
    private String message;
    /**构造方法*/
    ResultCode(int code, String message)
    {
        this.code = code;
        this.message = message;
    }
    /**
     * 通过code返回枚举
     */
    public static ResultCode parse(int code){
        ResultCode[] values = values();
        for (ResultCode error : values) {
            if(error.getCode() == code){
                return error;
            }
        }
        /**如果没有这个返回结果那么我将默认返回 500 错误*/
        return EXECUTING_REQUEST_ERROR;
    }
    public static void main(String[] args) {
        ResultCode errorCode = Arrays.stream(ResultCode.values()).filter(x -> x.getCode() == 1014031111).findAny().orElse(ResultCode.EXECUTING_REQUEST_ERROR);
        System.out.println(errorCode.getCode());
        System.out.println(errorCode.getMessage());
    }

}
