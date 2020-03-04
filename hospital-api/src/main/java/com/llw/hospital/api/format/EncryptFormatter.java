package com.llw.hospital.api.format;

import com.llw.hospital.api.util.AesUtils;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author wendellpeng
 * @Title: EncryptFormatter
 * @ProjectName gov-parent
 * @date 2018/9/4 15:41
 */
public class EncryptFormatter implements Formatter<String> {
    @Override
    public String parse(String s, Locale locale) throws ParseException {
        try {
            return AesUtils.decrypt("ANSBDUANXUHUAZUISHUAIHOUJIEMINSB",s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public String print(String s, Locale locale) {
        try {
            return AesUtils.encrypt("ANSBDUANXUHUAZUISHUAIHOUJIEMINSB",s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
