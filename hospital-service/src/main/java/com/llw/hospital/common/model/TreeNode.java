package com.llw.hospital.common.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/26.
 */
public class TreeNode implements Serializable{
    private String id;
    private String pid;
    private String name;
    private Integer level;
    private boolean isParent;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public boolean getIsParent() {
        return isParent;
    }
    public void setIsParent(boolean parent) {
        isParent = parent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
