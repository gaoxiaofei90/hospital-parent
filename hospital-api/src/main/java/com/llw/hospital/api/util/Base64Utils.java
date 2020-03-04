/**
 * Project Name:mbr-parent
 * Date:2017-08-25 16:48
 * Copyright (c) 2017, AEYE All Rights Reserved.
 */
package com.llw.hospital.api.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  

/**
 * Function: ADD FUNCTION. <br/>
 * Date: 2017-08-25 16:48 <br/>
 *
 * @author duanxuhua
 * @version 1.0
 */
public class Base64Utils {

    private static final char[] LEGAL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    /**
     * data[]进行编码
     * @param data
     * @return
     */
    public static String encode(byte[] data) {
        int start = 0;
        int len = data.length;
        StringBuffer buf = new StringBuffer(data.length * 3 / 2);

        int end = len - 3;
        int i = start;
        int n = 0;

        while (i <= end) {
            int d = ((((int) data[i]) & 0x0ff) << 16)
                    | ((((int) data[i + 1]) & 0x0ff) << 8)
                    | (((int) data[i + 2]) & 0x0ff);

            buf.append(LEGAL_CHARS[(d >> 18) & 63]);
            buf.append(LEGAL_CHARS[(d >> 12) & 63]);
            buf.append(LEGAL_CHARS[(d >> 6) & 63]);
            buf.append(LEGAL_CHARS[d & 63]);

            i += 3;

            if (n++ >= 14) {
                n = 0;
                buf.append(" ");
            }
        }

        if (i == start + len - 2) {
            int d = ((((int) data[i]) & 0x0ff) << 16)
                    | ((((int) data[i + 1]) & 255) << 8);

            buf.append(LEGAL_CHARS[(d >> 18) & 63]);
            buf.append(LEGAL_CHARS[(d >> 12) & 63]);
            buf.append(LEGAL_CHARS[(d >> 6) & 63]);
            buf.append("=");
        } else if (i == start + len - 1) {
            int d = (((int) data[i]) & 0x0ff) << 16;

            buf.append(LEGAL_CHARS[(d >> 18) & 63]);
            buf.append(LEGAL_CHARS[(d >> 12) & 63]);
            buf.append("==");
        }

        return buf.toString();
    }

    private static int decode(char c) {
        if (c >= 'A' && c <= 'Z') {
            return ((int) c) - 65;
        }
        else if (c >= 'a' && c <= 'z'){
            return ((int) c) - 97 + 26;
        }
        else if (c >= '0' && c <= '9'){
            return ((int) c) - 48 + 26 + 26;
        }
        else {
            switch (c) {
                case '+':
                    return 62;
                case '/':
                    return 63;
                case '=':
                    return 0;
                default:
                    throw new RuntimeException("unexpected code: " + c);
            }
        }
    }

    /**
     * Decodes the given Base64 encoded String to a new byte array. The byte
     * array holding the decoded data is returned.
     */

    public static byte[] decode(String s) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            decode(s, bos);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        byte[] decodedBytes = bos.toByteArray();
        try {
            bos.close();
            bos = null;
        } catch (IOException ex) {
            System.err.println("Error while decoding BASE64: " + ex.toString());
        }
        return decodedBytes;
    }

    private static void decode(String s, OutputStream os) throws IOException {
        int i = 0;

        int len = s.length();

        while (true) {
            while (i < len && s.charAt(i) <= ' ') {
                i++;
            }

            if (i == len) {
                break;
            }

            int tri = (decode(s.charAt(i)) << 18)
                    + (decode(s.charAt(i + 1)) << 12)
                    + (decode(s.charAt(i + 2)) << 6)
                    + (decode(s.charAt(i + 3)));

            os.write((tri >> 16) & 255);
            if (s.charAt(i + 2) == '=') {
                break;
            }
            os.write((tri >> 8) & 255);
            if (s.charAt(i + 3) == '=') {
                break;
            }
            os.write(tri & 255);

            i += 4;
        }
    }
    
    /**
     * @Description: 将base64编码字符串转换为图片
    */
    public static boolean generateImage(String imgStr, String imgFile) {
    	if (imgStr == null) //图像数据为空  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try{  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }   
            OutputStream out = new FileOutputStream(imgFile);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }catch (Exception e){ 
        	e.printStackTrace();
            return false;  
        }  
    }  
    
    /**
     * @Description: 根据图片地址转换为base64编码字符串
     */
    public static String getImageStr(String imgFile) {
            InputStream in = null;  
            byte[] data = null;  
            //读取图片字节数组  
            try   
            {  
                in = new FileInputStream(imgFile);          
                data = new byte[in.available()];  
                in.read(data);  
                in.close();  
            }   
            catch (IOException e)   
            {  
                e.printStackTrace();  
            }  
            //对字节数组Base64编码  
            BASE64Encoder encoder = new BASE64Encoder();  
            return encoder.encode(data);//返回Base64编码过的字节数组字符串  
        }  
    
}
