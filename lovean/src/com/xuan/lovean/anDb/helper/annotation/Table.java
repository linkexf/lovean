package com.xuan.lovean.anDb.helper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体类中标注为表名
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2013-4-8 下午3:41:46 $
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    public String name();
}
