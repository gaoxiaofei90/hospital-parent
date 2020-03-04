package com.llw.hospital.api;

import com.llw.hospital.vo.code.ColumnDetail;

import java.util.List;
import java.util.Map;

public interface SysGeneratorService {

    List<Map<String, String>> queryList(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, Object>> queryColumns(String tableName);

    byte[] generatorCode(String[] tableNames, List<ColumnDetail> columnDetails);

}
