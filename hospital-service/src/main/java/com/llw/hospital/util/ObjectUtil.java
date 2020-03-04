package com.llw.hospital.util;

public class ObjectUtil {
    public static boolean itemChanged(Object o1,Object o2){
        if(null != o1 && null != o2){
            return !o1.equals(o2);
        }else if(null == o1 && null == o2){
            return false;
        }else{
            return true;
        }
    }
}
