package com.llw.hospital.bs.filter.component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ActionMap {

    private static Map<String,ActionE> map;

    private static final Object lock=new Object();

    public static ActionE getActionE(String url){
        load();
        for(Map.Entry<String,ActionE> entry:map.entrySet()){
            if(url.endsWith(entry.getKey())){
                return entry.getValue();
            }
        }
        return null;
    }

    private static synchronized void load(){
        InputStream inputStream=null;
        BufferedReader bufferedReader=null;
        try {
        map = new HashMap<>();
        inputStream = ActionMap.class.getClassLoader().getResourceAsStream("RequestMap.json");
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        StringBuilder sb = new StringBuilder();
        String s;
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
            JSONArray jsonArray = JSONArray.parseArray(sb.toString());
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ActionE actionE = JSONObject.toJavaObject(jsonObject, ActionE.class);
                map.put(actionE.getUrl(), actionE);
            }
        } catch (IOException ignored) {
        } finally {
            try {
                inputStream.close();
                bufferedReader.close();
            } catch (IOException ignored) {
            }
        }
    }
    private static void init() {
        if (map == null) {
            synchronized (lock) {
                if (map == null) {
                    load();
                }
            }
        }
    }
}
