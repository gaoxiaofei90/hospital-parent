package com.llw.hospital.vo.user;

import com.jcl.orm.tkmapper.annotation.Calculated;
import com.llw.hospital.dto.SysUserDto;

public class UserVO extends SysUserDto {
    @Calculated(ognlExpression = "@com.llw.hospital.common.util.DictUtil@getDictLabel('enabled',#_thisObj.enabled)")
    private String enabledLabel;

    public String getEnabledLabel() {
        return enabledLabel;
    }

    public void setEnabledLabel(String enabledLabel) {
        this.enabledLabel = enabledLabel;
    }
}
