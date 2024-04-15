package com.example.usercenterbacked.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName:UserLoginRequest
 *
 * @Author tdc
 * Create 2024/4/11 15:00
 * Content
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -5320835172576919432L;
    String userAccount;
    String userPassword;
}
