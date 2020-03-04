package com.llw.hospital.bs.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.Annotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JacksonStdImpl
public class MyBeanPropertyWriter extends BeanPropertyWriter{

    private Logger logger = LoggerFactory.getLogger(com.llw.hospital.bs.json.MyBeanSerializerFactory.class);

    public MyBeanPropertyWriter(BeanPropertyDefinition propDef, AnnotatedMember member, Annotations contextAnnotations, JavaType declaredType, JsonSerializer<?> ser, TypeSerializer typeSer, JavaType serType, boolean suppressNulls, Object suppressableValue) {
        super(propDef, member, contextAnnotations, declaredType, ser, typeSer, serType, suppressNulls, suppressableValue);
    }

    @Override
    public void serializeAsField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
        super.serializeAsField(bean,gen,prov);
        /*if(_name.getValue().equals("orgId")){
            final Object value = (_accessorMethod == null) ? _field.get(bean)
                    : _accessorMethod.invoke(bean);
            gen.writeFieldName("orgInfo");
            Class<?> cls = String.class;
            PropertySerializerMap m = _dynamicSerializers;
            JsonSerializer<Object> ser = m.serializerFor(cls);
            if (ser == null) {
                ser = _findAndAddDynamic(m, cls, prov);
            }
            String orgName = "";
            if(null != value){
                try{
                    orgName = OrgUtil.getOrgName((Long)value);
                }catch (Exception e){
                    logger.warn("转换orgId为Long失败");
                    logger.warn(e.getMessage(),e);
                }
            }
            if (_typeSerializer == null) {
                ser.serialize(orgName, gen, prov);
            } else {
                ser.serializeWithType(orgName, gen, prov, _typeSerializer);
            }
        }*/
    }

}
