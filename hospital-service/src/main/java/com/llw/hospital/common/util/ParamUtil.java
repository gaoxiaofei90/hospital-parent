package com.llw.hospital.common.util;

import com.alibaba.fastjson.JSONObject;
import com.llw.cache.CacheClient;
import com.llw.hospital.common.base.constants.CacheConstants;
import com.llw.hospital.common.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ParamUtil {

    private static Logger logger = LoggerFactory.getLogger(ParamUtil.class);

    public static final String SYS_PARAM_ALGVERSION_PREFIX= "alg_version_";
    public static final String OSS_PARAM_EXTRACT_FEATURE_REALTIME= "oss_extract_feature_realtime";
    public static final String SYS_PARAM_THRESHOLD_PREFIX= "alg_threshold_";
    //百万级数据 阈值
    public static final String SYS_PARAM_MILLION_LEVEL_THRESHOLD_PREFIX= "alg_m_threshold_";
    //千万级数据 阈值
    public static final String SYS_PARAM_TEN_MILLION_LEVEL_THRESHOLD_PREFIX= "alg_tm_threshold_";

    public static final String SYS_PARAM_SURE_THRESHOLD_PREFIX= "alg_sure_threshold_";
    public static final String SYS_PARAM_DOMAIN_MAPPING_PREFIX= "domain_mapping_";
    public static final String SYS_PARAM_DOMAIN_CERT_PREFIX= "domain_certificate_";
    public static final String SYS_PARAM_DOMAIN_NTP_PREFIX= "domain_ntp_";
    public static final String SYS_PARAM_DOMAIN_STREAMER_PREFIX= "domain_streamer_";
    public static final String SYS_PARAM_ALIVE_CONFIG= "alive_config";
    public static final String SYS_PARAM_PREVIEW_STREAM_TYPE= "preview_stream_type";
    public static final String SYS_PARAM_MENTE_IDENTIFY_CYCLE = "mente_identify_cycle";
    public static final String SYS_PARAM_AUTH_URL = "alg_auth_url";
    public static final String SYS_PARAM_AUTH_CODE = "alg_auth_code";
    public static final String SYS_PARAM_DEVICE_REGISTER_INFO = "device_register_info";

    public static String getParam(String paramName){
        Map<String,String> map = (Map<String, String>) CacheClient.getCachedObject(CacheConstants.UPMS_PARAM_CACHE);
        return map.get(paramName);
    }
    
    public static Map<String,String> getParams(){
    	return (Map<String, String>) CacheClient.getCachedObject(CacheConstants.UPMS_PARAM_CACHE);
    }

    public static String getParam(String paramName,String defaultValue){
        try{
            Map<String,String> map = (Map<String, String>) CacheClient.getCachedObject(CacheConstants.UPMS_PARAM_CACHE);
            return !StringUtils.isEmpty(map.get(paramName)) ? map.get(paramName):defaultValue;
        }catch (Exception e){
            logger.error("获取参数{}失败",paramName);
        }
        return defaultValue;
    }

    public static JSONObject getJSONParam(String paramName,String defalutValue){
        String result = getParam(paramName,defalutValue);
        return JSONObject.parseObject(result);
    }
}
