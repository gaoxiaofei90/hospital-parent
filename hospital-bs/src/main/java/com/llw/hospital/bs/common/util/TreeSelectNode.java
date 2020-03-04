package com.llw.hospital.bs.common.util;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * treeSelect 树形下拉选择器节点类
 * @author Administrator
 *
 */
public class TreeSelectNode implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private String id;

	private String pid;
	
	private String name;
	
	private Boolean open;
	
	private Boolean checked; 
	
	private List<TreeSelectNode> children;

	private Integer type;
	
	public TreeSelectNode(String id, String pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
	}

	public TreeSelectNode() {
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

	
	@JSONField(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JSONField(name = "CHILDREN")
	public List<TreeSelectNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeSelectNode> children) {
		this.children = children;
	}
	
	
	@JSONField(name = "OPEN")
	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
	@JSONField(name = "CHECKED")
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	@JSONField(name = "TYPE")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", name=" + name + ", pid=" + pid + ", children=" + children + "}";
	}
}