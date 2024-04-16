package com.example.usercenterbacked.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.usercenterbacked.model.domain.User;
import com.example.usercenterbacked.model.domain.request.UserLoginRequest;
import com.example.usercenterbacked.model.domain.request.UserRegisterRequest;
import com.example.usercenterbacked.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.usercenterbacked.constant.UserConstant.ADMIN_ROLE;
import static com.example.usercenterbacked.constant.UserConstant.USER_LOGIN_STATE;

/**
 * ClassName:UserController
 *
 * @Author tdc
 * Create 2024/4/11 14:44
 * Content
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        long id = userService.userRegister(userAccount, userPassword, checkPassword);
        return id;
    }

    @PostMapping("/userLogin")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return user;

    }

    @GetMapping("/currentUser")
    public User getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        long id = currentUser.getId();
        if (currentUser == null) {
            return null;
        }
        currentUser = userService.getById(id);
        return currentUser;
    }

    @GetMapping("/search")
    public List<User> searchUser(String username, HttpServletRequest request) {

        if (!isAdmin(request)) {
            return new ArrayList<>();
        }
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        return userList.stream().map(user -> userService.getSafetyUser(user)
        ).collect(Collectors.toList());
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return false;
        }
        if (id <= 0) {
            return false;
        }
        return userService.removeById(id);
    }

    /**
     * 判断是否是管理员
     *
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        Object resultObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) resultObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
