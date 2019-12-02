package com.benwunet.mws.model.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解
 *
 * @author xaingkaihong
 * @date 2019/4/28 11:38
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PubLogSysLogAnnotation {

	/**
	 * 日志模块
	 *
	 * @return
	 *
	 */
	String module();

	/**
	 * 记录参数
	 * 尽量记录普通参数类型的方法，和能序列化的对象
	 * 
	 * @return
	 */
	boolean recordParam() default true;
}
