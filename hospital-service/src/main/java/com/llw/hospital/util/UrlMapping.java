package com.llw.hospital.util;

import java.lang.annotation.*;

/**
 * @author wendellpeng
 * 对指定字段进行url映射操作
 * @date 2018/9/4 15:40
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UrlMapping {
}