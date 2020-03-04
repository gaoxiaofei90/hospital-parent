package com.llw.hospital.api;

import com.llw.hospital.dto.SysPermissionDto;

import java.util.List;

/**
 * Created by shuzheng on 2017/2/11.
 */
public interface SysUpmsApiService {
    List<SysPermissionDto> selectPermissionByUserId(Long userId, int modules);
    List<SysPermissionDto> selectPermissionByUserId(Long userId);
}
