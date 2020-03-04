package com.llw.hospital.api.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wendellpeng
 * @Title: TokenResponse
 * @ProjectName gov-parent
 * @date 2018/9/4 11:16
 */
@ApiModel(value = "LoginResponse",description = "登录响应")
public class LoginResponse {


	 @ApiModelProperty(value = "token值" ,required = true)
	    private String token;

	    @ApiModelProperty(value = "有效时长，单位为小时")
	    private Integer effectiveTime;

	  /*  @ApiModelProperty(value = "用户类型，0：医院用户  1：中心用户")
	    private Integer userType;*/
	    
	    @ApiModelProperty(value = "经度")
	    private Double longitude;
	    
	    @ApiModelProperty(value = "纬度")
	    private Double latitude;

	    @ApiModelProperty(value = "登录名称")
	    private String name;
	    
	    @ApiModelProperty(value = "组织机构名称")
	    private String organizationName;
	    
	    public Integer getEffectiveTime() {
	        return effectiveTime;
	    }

	    public void setEffectiveTime(Integer effectiveTime) {
	        this.effectiveTime = effectiveTime;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }

		

		public Double getLongitude() {
			return longitude;
		}

		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}

		public Double getLatitude() {
			return latitude;
		}

		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getOrganizationName() {
			return organizationName;
		}

		public void setOrganizationName(String organizationName) {
			this.organizationName = organizationName;
		}

}
