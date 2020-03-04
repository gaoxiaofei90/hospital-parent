package com.llw.hospital.api.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "SysUser",description = "用户信息")
public class SysUser implements Serializable {

    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "用户账号")
    private String username;
    @ApiModelProperty(value = "用户头像")
    private String photo;
    @ApiModelProperty(value = "用户机构id")
    private Long orgId;
    @ApiModelProperty(value = "用户机构名称")
    private String orgIdLabel;
    @ApiModelProperty(value = "用户电话")
    private String phone;
    @ApiModelProperty(value = "用户姓名")
    private String name;
    @ApiModelProperty(value = "用户性别")
    private Integer sex;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getOrgIdLabel() {
        return orgIdLabel;
    }

    public void setOrgIdLabel(String orgIdLabel) {
        this.orgIdLabel = orgIdLabel;
    }
}
