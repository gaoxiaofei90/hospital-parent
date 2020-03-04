package com.llw.hospital.ds.mapper;

import com.llw.hospital.ds.sqlprovide.CodeSqlProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 */
public interface SysGeneratorDao {

	@SelectProvider(type = CodeSqlProvider.class, method = "queryList")
	List<Map<String, String>> queryList(Map<String, Object> map);

	@SelectProvider(type = CodeSqlProvider.class, method = "queryTotal")
	int queryTotal(Map<String, Object> map);

	@SelectProvider(type = CodeSqlProvider.class, method = "queryTable")
	Map<String, String> queryTable(String tableName);

	@SelectProvider(type = CodeSqlProvider.class, method = "queryColumns")
	List<Map<String, Object>> queryColumns(String tableName);
}
