package com.llw.hospital.api.factory;

import com.llw.hospital.api.annotation.Encrypt;
import com.llw.hospital.api.format.EncryptFormatter;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wendellpeng
 * @Title: EncryptFormatAnnotationFormatterFactory
 * @ProjectName gov-parent
 * @date 2018/9/415:44
 */
public class EncryptFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<Encrypt> {

    public EncryptFormatAnnotationFormatterFactory(){
        System.out.println("############# EncryptFormatAnnotationFormatterFactory inited ! ");

    }


    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> fieldTypes = new HashSet<>();
        fieldTypes.add(String.class);
        return fieldTypes;
    }

    @Override
    public Printer<?> getPrinter(Encrypt annotation, Class<?> fieldType) {
        return new EncryptFormatter();
    }

    @Override
    public Parser<?> getParser(Encrypt annotation, Class<?> fieldType) {
        return new EncryptFormatter();
    }
}