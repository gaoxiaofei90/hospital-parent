package com.llw.hospital.vo;

import com.llw.hospital.dto.SysOrganizationDto;

import java.util.List;

public class CachedSysOrganizationDto extends SysOrganizationDto {
    private List<String> busiTypes;
    private List<Integer> sysCodes;

    public List<String> getBusiTypes() {
        return busiTypes;
    }

    public void setBusiTypes(List<String> busiTypes) {
        this.busiTypes = busiTypes;
    }

    public List<Integer> getSysCodes() {
        return sysCodes;
    }

    public void setSysCodes(List<Integer> sysCodes) {
        this.sysCodes = sysCodes;
    }
}
