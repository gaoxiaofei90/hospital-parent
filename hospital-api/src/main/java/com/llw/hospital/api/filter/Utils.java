package com.llw.hospital.api.filter;

import com.alibaba.fastjson.JSONArray;

class Utils {
    static String toJSONStr(Object arguments) {
        try{
            return JSONArray.toJSONString(arguments);
        }catch (Exception e){
            return null;
        }
    }

//    public static <T> T getDubboService(Class<T> interf){
//        ApplicationConfig application=new ApplicationConfig();
//        application.setName("inst-ds");
//        RegistryConfig registryConfig =new RegistryConfig ();
//        registryConfig.setAddress("zookeeper://localhost:2181");
//        ReferenceConfig<T> rc= new ReferenceConfig<>();
//        rc.setApplication(application);
//        rc.setRegistry(registryConfig);
//        rc.setInterface(interf);
//        return rc.get();
//    }
}
