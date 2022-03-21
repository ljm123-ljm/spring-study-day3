package com.ljm.stereotype;

import java.lang.annotation.*;

/**
 * @Author jmle
 * @Date 2022/3/2 15:30
 * @Version 1.0
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";
}
