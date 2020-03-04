package com.llw.hospital.api.annotation;

import java.lang.annotation.*;

/**
 * @author wendellpeng
 * @Title: Encrypt
 * @ProjectName gov-parent
 * @Description: 关键接口字段加密
 * @date 2018/9/4 15:40
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {
}
