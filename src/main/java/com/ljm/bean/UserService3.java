package com.ljm.bean;

import com.ljm.stereotype.Component;

import java.util.Random;

/**
 * @Author jmle
 * @Date 2022/3/2 15:54
 * @Version 1.0
 */
public class UserService3 implements IUserService {
    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小傅哥，100001，深圳";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }
}
