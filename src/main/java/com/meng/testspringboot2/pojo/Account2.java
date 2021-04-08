package com.meng.testspringboot2.pojo;

import java.io.Serializable;

public class Account2 implements Serializable {

    private Integer id;

    private Integer uid;

    private double money;

//    对应一个用户
    private User2 user;

    public User2 getUser() {
        return user;
    }

    public void setUser(User2 user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                   "id=" + id +
                   ", uid=" + uid +
                   ", money=" + money +
                   '}';
    }
}
