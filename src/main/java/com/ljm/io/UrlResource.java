package com.ljm.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author jmle
 * @Date 2022/2/11 17:17
 * @Version 1.0
 */
public class UrlResource implements Resource {
    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url, "URL must not be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        try {
            URLConnection urlConnection = this.url.openConnection();
            return urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
