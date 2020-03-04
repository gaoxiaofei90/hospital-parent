/**
 * Project Name:mbr-ds-pt
 * File Name:SafeStringValidator.java
 * Package Name:com.aeye.mbr.ds.ui.validator
 * Date:2016年8月26日上午10:16:13
 * Copyright (c) 2016, shengpeng@a-eye.cn All Rights Reserved.
 *
*/

package com.llw.hospital.bs.common.ui.validator;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * ClassName:SafeStringValidator <br/>
 * Function: 安全字符串校验，防止跨站攻击，sql注入. <br/>
 * Date:     2016年8月26日 上午10:16:13 <br/>
 * @author   shengpeng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SafeStringValidator implements ConstraintValidator<SafeString, String> {

	@Override
	public void initialize(SafeString constraintAnnotation) {
		

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(!StringUtils.isEmpty(value))
		{
			if(value.contains("<") || value.contains(">") || value.contains("\"") || value.contains("\'") || value.contains("script") ||value.contains(";"))
			{
				return false;
			}
		}
		return true;
	}

}

