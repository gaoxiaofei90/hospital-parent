package com.llw.hospital.bs.json;


import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.PropertyBuilder;

public class MyBeanSerializerFactory extends BeanSerializerFactory {

    protected MyBeanSerializerFactory() {
        super(null);
    }

    @Override
    protected PropertyBuilder constructPropertyBuilder(SerializationConfig config,
                                                       BeanDescription beanDesc)
    {
        return new com.llw.hospital.bs.json.MyPropertyBuilder(config, beanDesc);
    }

}
