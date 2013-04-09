package com.xuan.lovean.anDb.helper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体类中标注为忽略改字段
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2013-4-8 下午3:42:06 $
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transient {

}
