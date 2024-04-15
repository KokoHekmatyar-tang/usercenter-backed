package com.example.usercenterbacked;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.usercenterbacked.mapper") 
public class UsercenterBackedApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsercenterBackedApplication.class, args);
    }

}
