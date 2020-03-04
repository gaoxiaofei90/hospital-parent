package com.llw.hospital.api.util;

import com.alibaba.fastjson.JSONObject;

public class MapUtil {

    public static <T> T mapObejctTo(Object obj,Class<T> clazz) {
        JSONObject jo= (JSONObject) JSONObject.toJSON(obj);
        return jo.toJavaObject(clazz);
    }
}
