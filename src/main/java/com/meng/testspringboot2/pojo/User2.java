package com.meng.testspringboot2.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User2 implements Serializable {
    private Integer id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

//    对应多个账户
    private List<Account2> accounts;

    public List<Account2> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account2> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
