package com.llw.hospital.vo.role;

import com.llw.hospital.dto.SysRoleDto;

public class UserRoleDto extends SysRoleDto {
    private boolean checked;
    private boolean disable;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
