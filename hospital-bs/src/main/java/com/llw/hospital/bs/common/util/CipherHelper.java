package com.llw.hospital.bs.common.util;

import java.security.MessageDigest;

public class CipherHelper {


    public static String md5(String input) {
        String result = input;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = bytesToHex(md.digest(input.getBytes()));
        } catch(Exception ex) {
            ex.printStackTrace();
            result = input;
        }
        return result;
    }

    /**
     * 16位md5
     * @param input
     * @return
     */
    public static String shortMd5(String input){
        String result = input;
        try {
            result = md5(input);
            result = result.substring(8, 24);
        }catch (Exception e){

        }
        return result;
    }

    public static final String byte2hexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString();
    }


    private static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];
            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString();
    }
}
