package com.answerlyl.myfinance.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述:加入此注解,就需要token
 *
 * @Author liyl21
 * @Date 2020/2/3 16:08
 **/
@Target({ElementType.METHOD, ElementType.TYPE})// 表明此注解可用在方法名上
@Retention(RetentionPolicy.RUNTIME)// 运行时有效
public @interface LoginRequired {
    boolean required() default true;
}
