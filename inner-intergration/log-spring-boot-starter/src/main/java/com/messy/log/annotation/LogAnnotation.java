package com.messy.log.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 * recordRequestParam:true需要配置log数据源
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

	/**
	 * 模块
	 * @return
	 */
	String module();

	/**
	 * 记录执行参数
	 * @return
	 */
	boolean recordRequestParam() default false;
}
