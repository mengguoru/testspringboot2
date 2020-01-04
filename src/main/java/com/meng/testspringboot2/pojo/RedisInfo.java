package com.meng.testspringboot2.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RedisInfo implements Serializable {
    private String key;
    private String msg;
    private String author;
}
