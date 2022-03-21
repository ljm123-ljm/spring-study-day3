package com.ljm.bean;

/**
 * @Author jmle
 * @Date 2022/2/25 14:48
 * @Version 1.0
 */
public interface IUserService {

    String queryUserInfo();

    String register(String userName);
}
