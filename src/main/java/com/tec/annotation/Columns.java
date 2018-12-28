package com.tec.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})            //字段名注解
@Retention(RetentionPolicy.RUNTIME)  //运行时注解
@Inherited                           //允许类或接口继承
@Documented                          //生成javadoc是包含注解
public @interface Columns {
    //如果注解只有一个成员,则成员名必须取为value(),在使用时可以忽略成员名和=
    String value();
}