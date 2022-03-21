package com.ljm.context.annotation;

import java.lang.annotation.*;

/**
 * @Author jmle
 * @Date 2022/3/2 15:35
 * @Version 1.0
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

   String value() default "singleton";
}
