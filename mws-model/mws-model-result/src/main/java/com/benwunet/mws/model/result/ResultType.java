package com.benwunet.mws.model.result;

import java.io.Serializable;
/**
 *  返回的类型
 *  @author WangLin
 *  @Date 2019/5/5 11:27
 */
public interface ResultType extends Serializable {
    String LIST = "list";
    String BEAN = "bean";
    String TEXT = "text";
    String NUMBER = "number";
    String BOOLEAN = "boolean";
    String PAGE_LIST = "pagelist";
}