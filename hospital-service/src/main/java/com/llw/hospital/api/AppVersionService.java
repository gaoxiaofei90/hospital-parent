package com.llw.hospital.api;

import com.jcl.orm.tkmapper.BaseDtoExtendService;
import com.llw.hospital.dto.AppVersionDto;

/**
 * APP版本信息
 *
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2019-07-24 10:50:47
 */
public interface AppVersionService extends BaseDtoExtendService<AppVersionDto> {

    int save(AppVersionDto appVersionDto);

    int update(AppVersionDto appVersionDto);
}

