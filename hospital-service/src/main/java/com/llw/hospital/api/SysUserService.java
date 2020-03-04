package com.llw.hospital.api;

import com.jcl.orm.tkmapper.BaseDtoExtendService;
import com.llw.hospital.dto.SysUserDto;

import java.util.List;

/**
 * 
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
public interface SysUserService extends BaseDtoExtendService<SysUserDto> {

    /**
     * 返回一个系统默认密码(明文)
     * @return
     */
    String newPassword();

    /**
     * 根据SysUserDto 新建用户,并返回
     * @param upmsUser
     * @return
     */
    SysUserDto createUser(SysUserDto upmsUser,List<Long> roleIds);


    void updateUser(SysUserDto sysUserDto,List<Long> roleIds);

    void deleteUser(Long pk);
}

