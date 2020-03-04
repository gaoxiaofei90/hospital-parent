package com.llw.hospital.ds.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.llw.hospital.api.SysGeneratorService;
import com.llw.hospital.ds.mapper.SysGeneratorDao;
import com.llw.hospital.ds.util.GenUtils;
import com.llw.hospital.vo.code.ColumnDetail;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Service
@Component
public class SysGeneratorServiceImpl implements SysGeneratorService {

    @Autowired
    private SysGeneratorDao sysGeneratorDao;

    @Override
    public List<Map<String, String>> queryList(Map<String, Object> map) {
        return sysGeneratorDao.queryList(map);
    }

    @Override
    public Map<String, String> queryTable(String tableName) {
        return sysGeneratorDao.queryTable(tableName);
    }

    @Override
    public List<Map<String, Object>> queryColumns(String tableName) {
        return sysGeneratorDao.queryColumns(tableName);
    }

    @Override
    public byte[] generatorCode(String[] tableNames, List<ColumnDetail> columnDetails) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, Object>> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns,columnDetails, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
