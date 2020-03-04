package com.llw.hospital.api.converter;

import com.llw.hospital.api.Exception.ParamInvalidException;
import com.llw.hospital.api.annotation.Encrypt;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author wendellpeng
 * @Title: EncryptConverter
 * @ProjectName gov-parent
 * @Description: 增加接口出参入参自动加解密功能,增加必填参数的校验，基于swagger annotation
 * @date 2018/9/4 16:46
 */
public class EncryptConverter implements HttpMessageConverter {

    private int typeDecrypt = 1;
    private int typeEncrypt = 2;

    String[] egnoreTypes = {"java.lang.Integer",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.Byte",
            "java.lang.Boolean",
            "java.lang.Character",
            //"java.lang.String",
            "int","double","long","short","byte","boolean","char","float"};

    private Logger logger = LoggerFactory.getLogger(EncryptConverter.class);

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    public EncryptConverter(HttpMessageConverter mappingJackson2HttpMessageConverter){
        this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
    }

    public HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        return mappingJackson2HttpMessageConverter;
    }

    public void setMappingJackson2HttpMessageConverter(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
        this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
    }

    @Override
    public boolean canRead(Class aClass, MediaType mediaType) {
        return mappingJackson2HttpMessageConverter.canRead(aClass,mediaType);
    }

    @Override
    public boolean canWrite(Class aClass, MediaType mediaType) {
        return mappingJackson2HttpMessageConverter.canWrite(aClass,mediaType);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return mappingJackson2HttpMessageConverter.getSupportedMediaTypes();
    }

    @Override
    public Object read(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Object obj = mappingJackson2HttpMessageConverter.read(aClass,httpInputMessage);
        changeFieldRecursion(obj, typeDecrypt);
        //基于swagger做一些参数校验
        validateFieldRecursion(obj);

        return obj;
    }

    private void validateFieldRecursion(Object obj) {
        if(null != obj){
            if(obj instanceof Map ){
                for(Object o : ((Map)obj).values())
                {
                    validateFieldRecursion(o);
                }
            }else
            if(obj instanceof Collection){
                for(Object o : ((Collection)obj))
                {
                    validateFieldRecursion(o);
                }
            }else
            if(obj instanceof Object[]){
                for(Object o : ((Object[])obj))
                {
                    validateFieldRecursion(o);
                }
            }
            else{
                Field[] fields = obj.getClass().getDeclaredFields();
                for(Field f:fields){
                    //只支持ApiModelProperty类型的注解
                    ApiModelProperty apiModelProperty = f.getAnnotation(ApiModelProperty.class);
                    if(apiModelProperty != null){
                        Object filedValue = null;
                        try {
                            filedValue = getFiledValue(obj,f.getName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //非空校验
                        if(apiModelProperty.required()){
                            if(null == filedValue){
                                throw new ParamInvalidException("缺少必填参数:" + f.getName());
                            }
                            //字符串为空校验
                            if("java.lang.String".equals(f.getType().getName()) && "".equals(filedValue))
                            {
                                throw new ParamInvalidException("缺少必填参数:" + f.getName());
                            }
                        }

                        //枚举值校验
                        if(null != apiModelProperty.allowableValues()){
                            if(null != filedValue && filedValue instanceof String){
                                //TODO 枚举值校验
                            }
                        }
                    }
                }
            }
        }
    }

    private void changeFieldRecursion(Object obj,int type) {
        if(null != obj){
            if(obj instanceof Map ){
                for(Object o : ((Map)obj).values())
                {
                    changeFieldRecursion(o,type);
                }
            }else
            if(obj instanceof Collection){
                for(Object o : ((Collection)obj))
                {
                    changeFieldRecursion(o,type);
                }
            }else{
                Field[] fields = obj.getClass().getDeclaredFields();
                for(Field f:fields){
                    //只支持String类型的注解
                    if(f.getAnnotation(Encrypt.class) != null && "java.lang.String".equals(f.getType().getName())){
                        String filedValue = null;
                        try {
                            filedValue = (String) getFiledValue(obj,f.getName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if(null != filedValue){
                            try {
                                if(typeDecrypt == type){
                                    //String decryptValue = AesUtils.decrypt(JWTFilter.getCurrentUser().getPassword(),filedValue);
                                    //setFiledValue(obj,f.getName(),decryptValue);
                                }else{
                                    //String encryptValue = AesUtils.encrypt(JWTFilter.getCurrentUser().getPassword(),filedValue);
                                    //setFiledValue(obj,f.getName(),encryptValue);
                                }
                            } catch (Exception e) {
                                //logger.error("failed to " + ((typeDecrypt ==type)?"decrypt":"encrypt") +" value of " + filedValue + " using password " + JWTFilter.getCurrentUser().getPassword());
                                logger.error(e.getMessage(),e);
                            }
                        }
                    }else{
                        Object filedValue = null;
                        try {
                            filedValue = getFiledValue(obj,f.getName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(null != filedValue){
                            changeFieldRecursion(filedValue,type);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void write(Object obj, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        //if(null != JWTFilter.getCurrentUser() && !StringUtils.isEmpty(JWTFilter.getCurrentUser().getPassword())){
            changeFieldRecursion(obj, typeEncrypt);
        //}
        mappingJackson2HttpMessageConverter.write(obj,mediaType,httpOutputMessage);
    }

    /**
     * 根据属性，调用set方法
     * @param ob 对象
     * @param name 属性名
     * @param  value 属性值
     * @return
     * @throws Exception
     */
    public static void setFiledValue(Object ob , String name,String value)throws Exception{
        Method[] m = ob.getClass().getMethods();
        for(int i = 0;i < m.length;i++){
            if(("set"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
                m[i].invoke(ob,value);
            }
        }
    }

    /**
     * 根据属性，调用get方法
     * @param ob 对象
     * @param name 属性名
     * @return
     * @throws Exception
     */
    public static Object getFiledValue(Object ob , String name)throws Exception{
        Method[] m = ob.getClass().getMethods();
        for(int i = 0;i < m.length;i++){
            if(("get"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
                return m[i].invoke(ob);
            }
        }
        return null;
    }
}
