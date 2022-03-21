package com.ljm.bean;

import com.ljm.stereotype.Component;

import java.util.Random;

/**
 * @Author jmle
 * @Date 2022/3/17 14:16
 * @Version 1.0
 */
@Component("userService")
public class UserService4 implements IUserService {

    private  String token;

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "乐嘉明,1001,上海";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户:"+userName +"success!";
    }

    @Override
    public String toString() {
        return "UserService4{" +
                "token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
