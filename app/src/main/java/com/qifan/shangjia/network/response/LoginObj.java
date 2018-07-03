package com.qifan.shangjia.network.response;

/**
 * Created by Administrator on 2018/5/8.
 */

public class LoginObj {

//     "real_name": "超级管理员",
//             "user_name": "admin",
//             "storeName": "XX运动鞋店",
//             "balance": 2000
    private  String id;
    private  String  real_name;
    private  String  user_name;
    private  String  storeName;
    private  String  balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
