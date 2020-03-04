package com.llw.hospital.ds.mapper;

import com.llw.hospital.ds.sqlprovide.CustomSqlProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Date;

/**
 * 
 * 
 * @author shengpeng
 * @email shengpeng@a-eye.com
 * @date 2018-11-05 11:12:22
 */
public interface SysCommonQueryMapper {
    /**
     * 根据key_name获取自定义增长id
     * @return
     */
    @SelectProvider(type = CustomSqlProvider.class, method = "nextVal")
    Long nextVal(String key);

    @SelectProvider(type = CustomSqlProvider.class, method = "currentTime")
    Date currentTime();
}
