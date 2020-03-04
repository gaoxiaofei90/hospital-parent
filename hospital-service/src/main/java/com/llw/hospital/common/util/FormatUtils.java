package com.llw.hospital.common.util;

import java.text.DecimalFormat;

public class FormatUtils {
    public  static DecimalFormat df = new DecimalFormat("#.00");

    /**
     * 将分格式化成元，保留两位小数
     * @param cents
     * @return
     */
    public static  String formatMoney(int cents){
        return df.format(cents/100);
    }

    /**
     * 将分格式评分，保留两位小数
     * @return
     */
    public static  String formatScore(double score){
        return df.format(score);
    }
}
