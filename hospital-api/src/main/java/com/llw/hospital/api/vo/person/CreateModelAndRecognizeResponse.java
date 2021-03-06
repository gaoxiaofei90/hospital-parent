package com.llw.hospital.api.vo.person;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "CreateModelAndRecognizeResponse", description = "建模和认证结果")
public class CreateModelAndRecognizeResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "建模和认证结果")
	private boolean success;

	@ApiModelProperty(value = "显示信息")
	private String message;

	@ApiModelProperty(value = "数据")
	private Object data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
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
