package com.meng.testspringboot2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.meng.testspringboot2.mapper")
@SpringBootApplication
public class Testspringboot2Application {

    public static void main(String[] args) {
        SpringApplication.run(Testspringboot2Application.class, args);
    }

}
