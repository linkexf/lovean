/* 
 * @(#)InjectView.java    Created on 2012-11-29
 * Copyright (c) 2012 ZDSoft Networks, Inc. All rights reserved.
 * $Id: InjectView.java 32935 2012-11-30 06:17:17Z xuan $
 */
package com.xuan.lovean.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动输入view的注解
 * 
 * @author xuan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectView {

	/**
	 * view的id索引
	 * 
	 * @return
	 */
	int value() default -1;

	/**
	 * 注解
	 * 
	 * @return
	 */
	String tag() default "";
}
