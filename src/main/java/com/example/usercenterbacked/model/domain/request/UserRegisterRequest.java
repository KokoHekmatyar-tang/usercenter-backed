package com.example.usercenterbacked.model.domain.request;

import lombok.Data;

import java.io.Serializable;


/**
 * ClassName:UserRegisterRequest
 * 用户注册请求体
 *
 * @Author tdc
 * Create 2024/4/11 14:50
 * Content
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -5741644497293168025L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

}
