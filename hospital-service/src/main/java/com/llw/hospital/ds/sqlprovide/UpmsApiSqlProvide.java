package com.llw.hospital.ds.sqlprovide;

import java.util.Map;

/**
 * Created by shengpeng on 18-11-5.
 */
public class UpmsApiSqlProvide {

    public String selectPermissionByUserIdInModules(Map<String,Object> params) {
        return "SELECT permission_id, pid, name, type, permission_value, uri, icon, enabled, orders\n" +
                "FROM sys_permission up\n" +
                "WHERE " +
                "up.enabled = 1 " +
                "AND up.modules = #{modules} " +
                "AND up.permission_id IN " +
                    "(SELECT permission_id\n" +
                    "FROM  sys_role_permission rp\n" +
                    "WHERE rp.role_id in (select role_id from sys_user_role where user_id = #{user_id} )" +
                ")\n" +
                "ORDER BY up.orders asc";
    }
}
