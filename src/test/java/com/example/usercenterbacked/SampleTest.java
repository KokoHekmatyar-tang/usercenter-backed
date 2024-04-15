package com.example.usercenterbacked;

import com.example.usercenterbacked.mapper.UserMapper;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class SampleTest {

    @Resource
    private UserMapper userMapper;



}