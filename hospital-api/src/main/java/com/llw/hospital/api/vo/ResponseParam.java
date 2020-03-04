/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.llw.hospital.api.vo;

import com.alibaba.fastjson.JSONObject;
import com.llw.hospital.api.util.HttpStatusCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 返回值
 */
@ApiModel(value = "ResponseParam",description = "通用响应")
public class ResponseParam<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "响应码，为200表示请求成功，非200请求异常，data没有数据返回，错误码请参考接口返回描述" ,required = true)
	private String code;

	@ApiModelProperty(value = "描述信息" ,required = true)
	private String message;

	@ApiModelProperty(value = "响应体，可能是数组，也可能是对象")
	private T data;

	@ApiModelProperty(value = "扩展数据，用于在接口没有正常返回的情况下返回客户端一些其他信息")
	private JSONObject extraData;

	@ApiModelProperty(value = "总记录")
	private Long totalCount;
	
	public JSONObject getExtraData() {
		return extraData;
	}

	public void setExtraData(JSONObject extraData) {
		this.extraData = extraData;
	}

	public T getData() {
		return data;
	}

	public ResponseParam setData(T data) {
		this.data = data;
		return this;
	}

	public ResponseParam setData(T data,long size) {
		this.data = data;
		this.totalCount = size;
		return this;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseParam() {
		this.setCode("200");
		this.setMessage("success");
	}

	public static ResponseParam error() {
		return error(String.valueOf(HttpStatusCode.INTERNAL_SERVER_ERROR.code()), "未知异常，请联系管理员");
	}

	public static ResponseParam error(String msg) {
		return error(String.valueOf(HttpStatusCode.INTERNAL_SERVER_ERROR.code()), msg);
	}

	public static ResponseParam error(String code, String msg) {
		ResponseParam r = new ResponseParam();
		r.setCode(code);
		r.setMessage(msg);
		return r;
	}

	public static ResponseParam ok(String msg) {
		ResponseParam r = new ResponseParam();
		r.setCode("200");
		r.setMessage(msg);
		return r;
	}

	public static ResponseParam unauth() {
		ResponseParam r = new ResponseParam();
		r.setCode(String.valueOf(HttpStatusCode.UNAUTHORIZED.code()));
		r.setMessage(HttpStatusCode.UNAUTHORIZED.reasonPhraseUS());
		return r;
	}

	public static ResponseParam ok() {
		return new ResponseParam();
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}


	
}