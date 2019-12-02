package com.benwunet.mws.model.result;

import lombok.Data;

/**
 *  @author WangLin
 *  @Date 2019/5/5 11:29
 */
@Data
public class ResultPageModel {
    public ResultPageModel(Integer code, String msg, Integer count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
    public ResultPageModel() {}
    /**
     *  状态码
     */
    private Integer code;
    /**
     *  信息
     */
    private String msg;
    /**
     *  返回值类型
     *   【list:集合、bean：对象、text：文本、boolean：逻辑值】
     */
    private Integer count;
    /**
     *  返回数据
     */
    private Object data;
}
