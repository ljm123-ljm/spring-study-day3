package com.ljm.bean;

import java.util.Random;

/**
 * @Author jmle
 * @Date 2022/2/25 14:49
 * @Version 1.0
 */
public class UserService2 implements IUserService {
    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "乐嘉明,1000,上海";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户:" + userName + "success!";
    }
}
