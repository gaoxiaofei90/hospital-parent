package com.llw.hospital.ds.sqlprovide;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class CodeSqlProvider {

    public String queryList(Map<String, Object> map){
        StringBuilder sql = new StringBuilder("");
        sql.append("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables \n" +
                   " where table_schema = (select database()) ");
        sql.append("and table_name like concat('%', #{tableName}, '%') ");
        sql.append(" order by create_time desc");
        return sql.toString();
    }

    public String queryList4Oracle(Map<String, Object> map){
        StringBuilder sql = new StringBuilder("select table_name \"tableName\",(select comments from USER_TAB_COMMENTS b where b.table_name = a.table_name) \"tableComment\" ,(select CREATED from USER_OBJECTS c where c.object_name = a.table_name) \"createTime\" from user_tables  a");
        sql.append(" where a.table_name like concat('%',concat(#{tableName}, '%')) order by \"createTime\" desc");
        return sql.toString();
    }

    public String queryTotal(Map<String, Object> map){
        StringBuilder sql = new StringBuilder("");
        sql.append("select count(*) from information_schema.tables where table_schema = (select database())");
        String tableName = map.containsKey("tableName") ? (String) map.get("tableName") : "";
        if(StringUtils.isNotBlank(tableName)){
            sql.append("and table_name like concat('%', #{tableName}, '%') ");
        }
        return sql.toString();
    }

    public String queryTable(String tableName){
        return "select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables " +
                " where table_schema = (select database()) and table_name = #{tableName}";
    }

    public String queryTable4Oracle(String tableName){
        StringBuilder sql = new StringBuilder("select table_name \"tableName\",(select comments from USER_TAB_COMMENTS b where b.table_name = a.table_name) \"tableComment\" ,(select CREATED from USER_OBJECTS c where c.object_name = a.table_name) \"createTime\" from user_tables  a");
        sql.append(" where a.table_name  = #{tableName} ");
        return sql.toString();
    }

    public String queryColumns(String tableName){
        return "select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns\n" +
                " where table_name = #{tableName} and table_schema = (select database()) order by ordinal_position";
    }

    public String queryColumns4Oracle(String tableName){
        return "select c.table_name,c.column_name \"columnName\",c.data_type \"dataType\",c.data_length,c.data_precision, m.comments \"columnComment\",cs.columnKey " +
                "from user_tab_columns c " +
                "left join user_col_comments m on c.table_name=m.table_name and c.column_name = m.column_name " +
                "left join (" +
                "  select col.column_name,'P' columnKey" +
                "  from user_constraints con,  user_cons_columns col " +
                "  where con.constraint_name = col.constraint_name " +
                "  and con.constraint_type='P' " +
                "  and col.table_name = #{tableName}" +
                ") cs on c.column_name = cs.column_name" +
                " where c.table_name=#{tableName}";
    }
}
