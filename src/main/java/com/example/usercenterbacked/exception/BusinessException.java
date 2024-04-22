package com.example.usercenterbacked.exception;

/**
 * ClassName:BusinessException
 *
 * @Author tdc
 * Create 2024/4/22 18:20
 * Content
 */

import com.example.usercenterbacked.common.ErrorCode;

/**
 * 自定义异常类
 *
 * @author
 */
public class  BusinessException extends RuntimeException {

    private final int code;

    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}