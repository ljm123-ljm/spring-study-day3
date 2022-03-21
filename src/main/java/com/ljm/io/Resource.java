package com.ljm.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author jmle
 * @Date 2022/2/11 17:15
 * @Version 1.0
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
