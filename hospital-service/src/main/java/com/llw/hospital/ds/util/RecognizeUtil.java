package com.llw.hospital.ds.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.llw.hospital.util.FileReadWriteUtil;

public class RecognizeUtil {
    /**
     * 从指定url列表读取图片
     * @param picUrls
     * @return
     */
    public static List<String> getPics(List<String> picUrls) {
        List<String> pics = new ArrayList<>(picUrls.size());
        for(String url:picUrls){
            byte[] data = FileReadWriteUtil.readFile(url);
            if(null != data){
                pics.add(Base64.encodeBase64String(data));
            }
        }
        return pics;
    }
}
