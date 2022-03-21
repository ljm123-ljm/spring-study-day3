package com.ljm.context;

import java.io.IOException;

/**
 * @Author jmle
 * @Date 2022/2/14 17:46
 * @Version 1.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * 刷新容器
     *
     * @return Result<XxxxDO>
     */
    void refresh() throws IOException;


    void registerShutdownHook();

    void close();
}
