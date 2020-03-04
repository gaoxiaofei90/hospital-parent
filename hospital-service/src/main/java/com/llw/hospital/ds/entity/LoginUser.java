package com.llw.hospital.ds.entity;

import com.aeye.master.util.JSONUtils;
import com.alibaba.dubbo.rpc.RpcContext;
import com.llw.hospital.common.base.constants.UpmsConstant;
import com.llw.hospital.dto.SysUserDto;

import java.util.List;

public class LoginUser extends SysUserDto {
	/**
     * 可操作的所有业务类型
     */
    private List<String> avaliableBusiTypes;
    /**
     * 可操作的所有系统类型
     */
    private List<Integer> avaliableSysCodes;
    /**
     * 可操作的统筹区
     */
    private String avaliableZoneCode;

    /**
     * 用户所属的机构类型 权限过滤时需要用到
     */
    private Integer orgCategory;

    public Integer getOrgCategory() {
        return orgCategory;
    }

    public void setOrgCategory(Integer orgCategory) {
        this.orgCategory = orgCategory;
    }

    public List<String> getAvaliableBusiTypes() {
        return avaliableBusiTypes;
    }

    public void setAvaliableBusiTypes(List<String> avaliableBusiTypes) {
        this.avaliableBusiTypes = avaliableBusiTypes;
    }

    public List<Integer> getAvaliableSysCodes() {
        return avaliableSysCodes;
    }

    public void setAvaliableSysCodes(List<Integer> avaliableSysCodes) {
        this.avaliableSysCodes = avaliableSysCodes;
    }

    public String getAvaliableZoneCode() {
        return avaliableZoneCode;
    }

    public void setAvaliableZoneCode(String avaliableZoneCode) {
        this.avaliableZoneCode = avaliableZoneCode;
    }

    public void setToRpcContext(RpcContext rpcContext) {
        rpcContext.setAttachment(UpmsConstant.LOGIN_USER_ID, this ==null?null: getUserId()+"");
        //所属机构
        if(null != this && null != getOrgId()){
            rpcContext.setAttachment(UpmsConstant.LOGIN_USER_ORG_ID,String.valueOf(getOrgId()));
        }
        rpcContext.setAttachment(UpmsConstant.LOGIN_USER, this ==null?null: JSONUtils.toJSONString(this));
    }

    public static LoginUser getFromRpcContext(RpcContext rpcContext){
        LoginUser loginUser = JSONUtils.parseObject(rpcContext.getAttachment(UpmsConstant.LOGIN_USER),LoginUser.class);
        return loginUser;
    }
}
