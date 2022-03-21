package com.ljm.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author jmle
 * @Date 2022/2/10 18:59
 * @Version 1.0
 */
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    public void initDataMethod(){
        System.out.println("执行：init-method");
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public String queryUserName(String uid) {
        return hashMap.get(uid);
    }
    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }
}
