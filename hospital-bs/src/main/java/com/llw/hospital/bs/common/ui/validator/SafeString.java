/**
 * Project Name:mbr-ds-pt
 * File Name:SafeString.java
 * Package Name:com.aeye.mbr.ds.ui.validator
 * Date:2016年8月26日上午10:15:39
 * Copyright (c) 2016, shengpeng@a-eye.cn All Rights Reserved.
 *
*/

package com.llw.hospital.bs.common.ui.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * ClassName:SafeString <br/>
 * Date:     2016年8月26日 上午10:15:39 <br/>
 * @author   shengpeng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Documented
@Constraint(validatedBy = { com.llw.hospital.bs.common.ui.validator.SafeStringValidator.class })
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SafeString {
	 String message() default "{id}";
     
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
}

