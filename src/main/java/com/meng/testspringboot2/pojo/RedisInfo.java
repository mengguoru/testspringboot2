package com.meng.testspringboot2.pojo;

import java.io.Serializable;

public class RedisInfo implements Serializable {

    private String key;

    private String msg;

    private String author;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "RedisInfo{" +
                   "key='" + key + '\'' +
                   ", msg='" + msg + '\'' +
                   ", author='" + author + '\'' +
                   '}';
    }
}
