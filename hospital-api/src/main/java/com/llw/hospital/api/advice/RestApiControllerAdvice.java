package com.llw.hospital.api.advice;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.llw.hospital.api.Exception.ParamInvalidException;
import com.llw.hospital.api.vo.ResponseParam;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wendellpeng
 * @Title: RestApiControllerAdvice
 * @ProjectName gov-parent
 * @Description: 异常处理切面
 * @date 2018/9/3 20:00
 */
@ControllerAdvice
public class RestApiControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(RestApiControllerAdvice.class);
    @ExceptionHandler(value=UnauthorizedException.class)
    @ResponseBody
    public ResponseParam handle(UnauthorizedException e) {
        logger.error(e.getMessage(),e);
        // *记入异常日志
        return ResponseParam.unauth();
    }


    @ExceptionHandler(value=ExpiredJwtException.class)
    @ResponseBody
    public ResponseParam handle(ExpiredJwtException e) {
        logger.error(e.getMessage(),e);
        // *记入异常日志
        return ResponseParam.error("403","token已过期");
    }

    @ExceptionHandler(value=UnrecognizedPropertyException.class)
    @ResponseBody
    public ResponseParam handle(UnrecognizedPropertyException e) {
        logger.error(e.getMessage(),e);
        // *记入异常日志
        if(logger.isDebugEnabled()){
            return  ResponseParam.error(e.getMessage());
        }else{
            return ResponseParam.error("参数不合法，报文中存在多余的参数");
        }
    }

    @ExceptionHandler(value= ParamInvalidException.class)
    @ResponseBody
    public ResponseParam handle(ParamInvalidException e) {
        logger.error(e.getMessage(),e);
        // *记入异常日志
        return  ResponseParam.error("1001",e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseParam exception(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<ErrorMsg> errorMsgs = new ArrayList<>();

        allErrors.forEach(objectError -> {
            FieldError fieldError = (FieldError)objectError;
            logger.error(fieldError.getField()+":"+fieldError.getDefaultMessage());
        });
        return ResponseParam.error("参数不合法");
    }

    @ExceptionHandler(value=RuntimeException.class)
    @ResponseBody
    public ResponseParam handle(RuntimeException e) {
        logger.error(e.getMessage(),e);
        // *记入异常日志
        if(logger.isDebugEnabled()){
            return ResponseParam.error(e.getMessage());
        }else{
            return ResponseParam.error("服务异常");
        }
    }

    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public ResponseParam handle(Exception e) {
        logger.error(e.getMessage(),e);
        if(logger.isDebugEnabled()){
            return ResponseParam.error(e.getMessage());
        }else{
            return ResponseParam.error("服务异常");
        }
    }

}