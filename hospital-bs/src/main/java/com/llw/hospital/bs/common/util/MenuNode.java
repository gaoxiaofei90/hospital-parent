package com.llw.hospital.bs.common.util;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class MenuNode implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String pid;

	private Integer type;

	private String name;

	private String topid;
	
	private String pname;
	
	private List<MenuNode> children;

	private String label;
	
	 private String deviceCode;
	    
    private String cameraIndexCode;
    
    private String rtspUrl;
    
    private String deviceNetStatus;
    
    private String checked;
    
    private String key;
    
	public MenuNode(String id, String pid, String name,String label) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.label = label;
	}

	public MenuNode() {
		super();
	}

	@JSONField(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	@JSONField(name = "PID")
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

	@JSONField(name = "TYPE")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@JSONField(name = "TOPID")
	public String getTopid() {
		return topid;
	}

	public void setTopid(String topid) {
		this.topid = topid;
	}
	@JSONField(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JSONField(name = "CHILDREN")
	public List<MenuNode> getChildren() {
		return children;
	}

	public void setChildren(List<MenuNode> children) {
		this.children = children;
	}
	
	@JSONField(name = "PNAME")
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getCameraIndexCode() {
		return cameraIndexCode;
	}

	public void setCameraIndexCode(String cameraIndexCode) {
		this.cameraIndexCode = cameraIndexCode;
	}

	public String getRtspUrl() {
		return rtspUrl;
	}

	public void setRtspUrl(String rtspUrl) {
		this.rtspUrl = rtspUrl;
	}

	public String getDeviceNetStatus() {
		return deviceNetStatus;
	}

	public void setDeviceNetStatus(String deviceNetStatus) {
		this.deviceNetStatus = deviceNetStatus;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", name=" + name + ", pid=" + pid + ", children=" + children + ", label=" + label + "}";
	}
}