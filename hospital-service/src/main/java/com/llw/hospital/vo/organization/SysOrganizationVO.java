package com.llw.hospital.vo.organization;

import com.jcl.orm.tkmapper.annotation.Calculated;
import com.llw.hospital.dto.SysOrganizationDto;

public class SysOrganizationVO extends SysOrganizationDto {
    //机构类型
    @Calculated(ognlExpression = "@com.llw.hospital.common.util.DictUtil@getDictLabel('orgCategory',#_thisObj.orgCategory)")
    private String orgCategoryLabel;

    public String getOrgCategoryLabel() {
        return orgCategoryLabel;
    }

    public void setOrgCategoryLabel(String orgCategoryLabel) {
        this.orgCategoryLabel = orgCategoryLabel;
    }
}
