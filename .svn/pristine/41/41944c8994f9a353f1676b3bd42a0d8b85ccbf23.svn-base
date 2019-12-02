package com.benwunet.mws.model.result;

import lombok.Data;

/**
 *  @author WangLin
 *  @Date 2019/4/20 17:02
 */
@Data
public class ResultModel {
    public ResultModel(Integer status, String msg, String type, Object data) {
        this.status = status;
        this.msg = msg;
        this.type = type;
        this.data = data;
    }
    public static final String LIST = "list";
    public static final String BEAN = "bean";
    public static final String TEXT = "text";
    public static final String BOOLEAN = "boolean";
    public ResultModel() {}
    /**
     *  状态码
     */
    private Integer status;
    /**
     *  信息
     */
    private String msg;
    /**
     *  返回值类型
     *   【list:集合、bean：对象、text：文本、boolean：逻辑值】
     */
    private String type;
    /**
     *  返回数据
     */
    private Object data;


}
