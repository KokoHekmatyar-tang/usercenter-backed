package com.example.usercenterbacked.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.usercenterbacked.model.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;


/**
 * ClassName:UserServiceTest
 *
 * @Author tdc
 * Create 2024/4/9 19:40
 * Content
 */
@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;
    private static final String SALT = "overWatch";

    @Test
    public void addUser() {
        User user = new User();
        user.setId(0L);
        user.setUsername("");
        user.setUserAccount("");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setUserPassword("");
        user.setPhone("");
        user.setEmail("");
        user.setUserStatus(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsDelete(0);
        user.setUserRole(0);
        Boolean result = userService.save(user);
        Assertions.assertTrue(result);
    }

    @Test
    void testDigest() {
        String newPassword = DigestUtils.md5DigestAsHex(("abc" + "newPassword").getBytes());
        System.out.println(newPassword);
    }

    @Test
    void userRegister() {
        String userAccount = "test";
        String userPassword = "12345678";

        String newPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        System.out.println(newPassword);

        //≤Â»Î ˝æ›
        User user = new User();
        user.setId(0L);
        user.setUsername("");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setPhone("");
        user.setEmail("");
        user.setUserStatus(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsDelete(0);
        user.setUserRole(0);
        user.setUserAccount(userAccount);
        user.setUserPassword(newPassword);
        boolean save = userService.save(user);
        System.out.println(save+"--testResult");
    }
}