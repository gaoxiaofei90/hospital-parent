package com.llw.hospital.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class FileReadWriteUtil {

    private static Logger log = LoggerFactory.getLogger(FileReadWriteUtil.class);

    private static RestTemplate restTemplate = new RestTemplate();

    private static Map<String,String> dnsMapping = new HashMap(1);



    /**
     * @param url 读取网络文件的完整地址 例如: http://127.0.0.1:8080/avatar/100000/abc.jpg
     * @return
     */
    public static byte[] readFile(String url) {
        try {
            if(StringUtils.isNotEmpty(url)) {
                url = getMappingUrl(url);

                HttpHeaders headers = new HttpHeaders();
                HttpEntity<Resource> httpEntity = new HttpEntity<Resource>(headers);
                ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class);
                if(response != null) {
                    return response.getBody();
                }
            }
        }catch (Exception e) {
            log.error(e.getMessage(),e);
            return null;
        }
        return null;
    }

    private static String getMappingUrl(String url) {
        if(!dnsMapping.isEmpty()){
            for(String key:dnsMapping.keySet()){
                if(url.contains(key)){
                    return url.replaceAll(key,dnsMapping.get(key));
                }
            }
        }
        return url;
    }
}