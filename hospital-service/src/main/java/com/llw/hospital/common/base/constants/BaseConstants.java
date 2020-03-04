package com.llw.hospital.common.base.constants;

/**
 * 全局常量
 * Created by shengpeng on 2018/2/18.
 */
public class BaseConstants {

    public static final String MODULE_NAME = "hospital";

    public static final long SUPER_ORG_ID = 0;
    public static final Long DEFAULT_ROLE_ID = 1L;
    public static final String ORG_ID = "orgId";

    public static final String CACHE_REFRESH_TYPE = "fresh_type";
    public static final String CACHE_REFRESH_TYPE_DELTA = "delta";

    public static final int STATUS_NORMAL = 1;
    public static final int STATUS_FORBIDDEN = 0;

    //权限模块 ds
    public static final int MODULES_BS = 0 ;
    //权限模块 app
    public static final int MODULES_APP = 1 ;


    public static final Integer DELETED = 1;
    public static final Integer NOT_DELETED = 0;

    public static final String DEFAULT_PASSWORD = "123456";
    public static final Integer MODEL_STATUS_NOMAL = 10;

    public static final Integer ORG_CATEGORY_GROUP = 1;//虚拟分组
    public static final Integer ORG_CATEGORY_INSTITUTION = 2;//医保中心
    public static final Integer ORG_CATEGORY_HOSPITAL = 3;//医院
    public static final Integer ORG_CATEGORY_department = 4;//科室
}
