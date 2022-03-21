package com.ljm.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author jmle
 * @Date 2022/2/11 17:26
 * @Version 1.0
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) throws MalformedURLException {
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
}
