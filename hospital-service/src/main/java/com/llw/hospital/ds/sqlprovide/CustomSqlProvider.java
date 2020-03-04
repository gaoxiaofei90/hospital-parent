package com.llw.hospital.ds.sqlprovide;

/**
 * 自定义sql
 */
public class CustomSqlProvider {

	/**
	 * 根据key_name获取自定义增长id
	 * 
	 * @param sequenceName
	 * @return
	 */
	public String nextVal(String sequenceName) {
		return " select nextval ('" + sequenceName + "')";
	}

	/**
	 * 根据key_name获取自定义增长id
	 *
	 * @param sequenceName
	 * @return
	 */
	public String nextVal4Oracle(String sequenceName) {
		return " select " + sequenceName + ".nextval from dual";
	}

	public String currentTime(){
		return "select current_timestamp()";
	}

	public String currentTime4Oracle(){
		return "select sysdate from dual";
	}
}
