/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package cn.coolink.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 免登陆注解
 * @author
 * @Date 2017年3月6日
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EscapeLogin {
	
	/**
	 *  token 参数名
	 *  @return
	 */
	String value() default "";

}