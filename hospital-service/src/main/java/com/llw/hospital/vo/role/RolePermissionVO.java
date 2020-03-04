package com.llw.hospital.vo.role;

import com.llw.hospital.dto.SysPermissionDto;

public class RolePermissionVO extends SysPermissionDto {

    private Integer checked;

    private Integer disabled;

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }
}
