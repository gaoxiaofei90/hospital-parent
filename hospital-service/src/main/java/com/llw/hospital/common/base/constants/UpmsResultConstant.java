package com.llw.hospital.common.base.constants;

/**
 * @author wendellpeng
 * @Title: JWTFilter
 * @ProjectName gov-parent
 * 结果常量
 * @date 2018/8/2719:57
 */
public enum UpmsResultConstant {

    FAILED(1, "failed"),
    SUCCESS(0, "success"),

    INVALID_LENGTH(10001, "Invalid length"),
    EMPTY_USERNAME(10101, "Username cannot be empty"),
    EMPTY_PASSWORD(10102, "Password cannot be empty"),
    INVALID_USERNAME(10103, "Account does not exist"),
    INVALID_PASSWORD(10104, "Password error"),
    INVALID_ACCOUNT(10105, "Invalid account"),
    EXIST_ACCOUNT(10106,"Exist account"),
    NOT_LOGIN(10107,"Not login");

    public int code;

    public String message;

    UpmsResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
