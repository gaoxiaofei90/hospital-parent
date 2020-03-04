package com.llw.hospital.api.converter;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import springfox.documentation.spring.web.json.Json;

/**
 * @author wendellpeng
 * @Title: FastJsonHttpMessageConverterEx
 * @ProjectName gov-parent
 * @date 2018/9/714:55
 */
public class FastJsonHttpMessageConverterEx extends FastJsonHttpMessageConverter {

    public FastJsonHttpMessageConverterEx() {
        super();
        this.getFastJsonConfig().getSerializeConfig().put(Json.class, SwaggerJsonSerializer.INSTANCE);
    }

}
