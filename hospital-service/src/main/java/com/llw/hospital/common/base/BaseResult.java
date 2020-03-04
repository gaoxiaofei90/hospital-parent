package com.llw.hospital.common.base;

import java.io.Serializable;

/**
 * 统一返回结果类
 * Created by shuzheng on 2017/2/18.
 */
public class BaseResult implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 状态码：1成功，其他为失败
    public int code;

    // 成功为success，其他为失败原因
    public String message;

    // 数据结果集
    public Object data;

    public static final Integer SUCCESS = 1;

    public static final Integer SEVER_ERROR = 500;

    public static BaseResult success(String message){
        return new BaseResult(SUCCESS, message, true);
    }

    public static BaseResult success(Object data){
        return new BaseResult(SUCCESS, "操作成功", data);
    }

    public static BaseResult fail(String message){
        return new BaseResult(SEVER_ERROR, message, false);
    }

    public BaseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
