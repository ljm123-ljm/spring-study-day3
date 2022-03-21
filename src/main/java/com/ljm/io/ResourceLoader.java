package com.ljm.io;

import java.net.MalformedURLException;

/**
 * @Author jmle
 * @Date 2022/2/11 17:16
 * @Version 1.0
 */
public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location) throws MalformedURLException;
}
