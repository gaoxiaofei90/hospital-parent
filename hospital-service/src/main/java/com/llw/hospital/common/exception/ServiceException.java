/**
 * Project Name:mbr-pts-medical
 * File Name:ServiceException.java
 * Package Name:com.aeye.mbr.pts.medical.exception
 * Date:2015年8月21日下午3:21:28
 * Copyright (c) 2015, shengpeng@a-eye.cn All Rights Reserved.
 *
*/

package com.llw.hospital.common.exception;

import com.jcl.constant.ErrorCodeConstants;

/**
 * ClassName:ServiceException <br/>
 * Function: service层异常对象<br/>
 * Reason:	 规范异常码和描述. <br/>
 * Date:     2015年8月21日 下午3:21:28 <br/>
 * @author   shengpeng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ServiceException extends com.jcl.exception.ServiceException {
	private String errorCode = "";
	public ServiceException() {
	}

	public ServiceException(ErrorCodeConstants ec) {
		super(ec);
	}

	public ServiceException(ErrorCodeConstants ec, String cause) {
		super(ec, cause);
	}

	public ServiceException(String errorCode, String cause) {
		super(ErrorCodeConstants.FAILD, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}

