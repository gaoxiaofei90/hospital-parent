package com.llw.hospital.bs.util;

/**
 * 常量定义
 */
public class Constants {
    /**
     * 自理能力评估等级 1 自理 2 介助 3 介护
     */
    public enum selfcareAbility{
        ZILI(1, "自理"),JIEZHU(2, "介助"),JIEHU(3, "介护");
        public int val=0;
        public String name;
        selfcareAbility(int val, String name){
            this.val = val;
            this.name = name;
        }
    }
}
