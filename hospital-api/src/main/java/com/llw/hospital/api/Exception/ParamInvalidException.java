package com.llw.hospital.api.Exception;

/**
 * @author wendellpeng
 * @Title: ParamInvalidException
 * @ProjectName gov-parent
 * @Description: 接口入参校验异常
 * @date 2018/9/11 11:15
 */
public class ParamInvalidException extends RuntimeException {
    public ParamInvalidException(String message){
        super(message);
    }
}
