package com.llw.hospital.dto;


import com.jcl.dto.BaseDto;

public class SysDemoExtendDto extends BaseDto {
    private Long demoId;
    private String name;
    private Long extendId;

    public Long getDemoId() {
        return demoId;
    }

    public void setDemoId(Long demoId) {
        this.demoId = demoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getExtendId() {
        return extendId;
    }

    public void setExtendId(Long extendId) {
        this.extendId = extendId;
    }
}
