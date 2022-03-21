package com.ljm.util;

/**
 * @Author jmle
 * @Date 2022/2/11 17:22
 * @Version 1.0
 */
public class ClassUtil {
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader c1;
        c1 = Thread.currentThread().getContextClassLoader();
        if (c1 == null) {
            c1 = ClassUtil.class.getClassLoader();
        }
        return c1;
    }

    public static  boolean isCglibProxyClass(Class<?> clazz){
        return clazz!=null &&isCglibProxyClassName(clazz.getName());
    }


    public static  boolean isCglibProxyClassName(String className){
        return className!=null && className.contains("$$");
    }
}
