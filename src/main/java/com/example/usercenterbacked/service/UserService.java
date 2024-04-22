package com.example.usercenterbacked.service;

import com.example.usercenterbacked.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.net.httpserver.HttpServer;

import javax.servlet.http.HttpServletRequest;

/**
* @author ye
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-04-09 19:25:01
*/
public interface UserService extends IService<User> {
    /**
     *
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @return
     */
    long userRegister(String userAccount,String userPassword,String checkPassword);
    /**
     *
     * @param userAccount
     * @param userPassword
     * @return
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * @description:用户脱敏
     * @param user
     * @return
     */
    User getSafetyUser(User user);

    /**
     * 用户注销
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);
}
