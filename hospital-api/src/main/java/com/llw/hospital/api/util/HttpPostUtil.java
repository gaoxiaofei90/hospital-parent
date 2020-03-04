package com.llw.hospital.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 封装一个请求政府端的httppost请求方法
 * 
 * @title HttpPostUtil.java
 *
 * @author qinliang
 * @version 1.0
 * @created 2018年9月13日
 */
public class HttpPostUtil {

    /**
     * 获取政府访问接口
     * 
     * @param url
     * @param json
     * @param token
     * @param charset
     * @param contentType
     * @return
     */
    public static JSONObject doPost(String url, String json, String token, String charset, String contentType) {
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            post.addHeader("access_token", token); // 传入访问token
            StringEntity s = new StringEntity(json, charset); // 中文乱码在此解决
            s.setContentType(contentType);
            post.setEntity(s);
            HttpResponse res = HttpClients.createDefault().execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSON.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static JSONObject httpPostWithForm(String url, Map<String, Object> params) {
        HttpPost post = new HttpPost(url);
        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
        String access_token = String.valueOf(params.get("access_token"));
        pairList.add(new BasicNameValuePair("access_token", access_token));
        List<String> userIds = (List<String>) params.get("toUserIds");
        for (String userId : userIds) {
            pairList.add(new BasicNameValuePair("toUserIds", userId));
        }
        JSONObject response = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
            HttpResponse res = HttpClients.createDefault().execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSON.parseObject(result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public static void main(String[] args) {
        String url = "http://192.168.16.102:8092/friends/relations";
        Map<String, Object> contentMap = Maps.newConcurrentMap();
        List<String> userIds = Lists.newArrayList();
        userIds.add("10000162");
        userIds.add("10000157");
        userIds.add("10000009");
        contentMap.put("toUserIds", userIds);
        contentMap.put("access_token", "test_18608407967");
        System.out.println(httpPostWithForm(url, contentMap));
    }
}
