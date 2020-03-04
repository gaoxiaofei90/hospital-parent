package com.llw.hospital.common.base.constants;


public class UpmsDatabaseConstants {

    public static final String SCHEMA_UPMS = "";

    public static final String TABLE_UPMS_USER = SCHEMA_UPMS + "upms_user";

    public static final String TABLE_UPMS_PARAM= SCHEMA_UPMS + "upms_param";

    public static final String TABLE_UPMS_ORGANIZATION = SCHEMA_UPMS + "upms_organization";

    public static final String TABLE_UPMS_ROLE = SCHEMA_UPMS + "upms_role";

    public static final String TABLE_UPMS_PERMISSION = SCHEMA_UPMS + "upms_permission";

    public static final String TABLE_UPMS_USER_PERMISSION = SCHEMA_UPMS + "upms_user_permission";

    public static final String TABLE_UPMS_ROLE_PERMISSION = SCHEMA_UPMS + "upms_role_permission";

    public static final String TABLE_UPMS_SYSTEM = SCHEMA_UPMS + "upms_system";

    public static final String TABLE_UPMS_USER_ROLE = SCHEMA_UPMS + "upms_user_role";

    public static final String TABLE_DICT = "upms_dict";//数据字典

    public static final String TABLE_AREA = "upms_area";//行政区划

    public static final String UPMS_AUDIT_ROLE = "upms_audit_role";
    public static final String UPMS_ACCESS_USER = "upms_access_user";
    public static final String LOGIN_LOG = "login_log";
    public static final String OPER_LOG = "oper_log";
    public static final String UPMS_LOG = "upms_log";
    
    public static final String TABLE_BASE_GUARDIAN = "base_guardian";//监护人
    
    public static final String TABLE_BASE_IDENTIFICATION_RECORD = "base_identification_record";//认证记录
    
    public static final String TABLE_BASE_MERCHANT = "base_merchant";//社区服务商
    
    public static final String TABLE_BASE_RESIDENT= "base_resident";//居民信息详情
    
    public static final String TABLE_BASE_RESIDENT_FEATURE = "base_resident_feature";//生物特征
    
    public static final String TABLE_BASE_RESIDENT_SICK = "base_resident_sick";//患病列表
    
    public static final String TABLE_COMMUNITY_HOTLINE = "community_hotline";//社区电话
    
    public static final String TABLE_SITE_ORDER = "site_order";//订单表
}
