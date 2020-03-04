package com.llw.hospital.util;

import com.llw.hospital.dto.SysOrganizationDto;

public interface OrgFilter {
    boolean accept(SysOrganizationDto sysOrganizationDto);
}
