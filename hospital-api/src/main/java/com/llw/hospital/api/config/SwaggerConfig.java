package com.llw.hospital.api.config;

import org.apache.shiro.codec.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.llw.hospital.api"})
//@EnableWebMvc
*/
@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"com.llw.hospital.api"})
public class SwaggerConfig extends WebMvcConfigurationSupport {

	/*
    @Bean
    public Docket customDocket() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(401).message("未授权").build());
        responseMessageList.add(new ResponseMessageBuilder().code(403).message("Token已过期").build());
        responseMessageList.add(new ResponseMessageBuilder().code(404).message("找不到资源").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").build());
        responseMessageList.add(new ResponseMessageBuilder().code(1001).message("参数错误").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.POST,responseMessageList)
                .directModelSubstitute(Date.class, Long.class)
                .apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("aeye", "www.a-eye.cn", "mail.a-eye.cn");
        return new ApiInfo("医保监管平台API接口",//大标题 title
                "Swagger2",//小标题
                "1.0",//版本
                "www.a-eye.cn",//termsOfServiceUrl
                contact,//作者
                "GPL",//链接显示文字
                "www.a-eye.cn"//网站链接
        );
    }
	*/
	@Bean
    public Docket createRestApi() {

        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(401).message("未授权").build());
        responseMessageList.add(new ResponseMessageBuilder().code(403).message("Token已过期").build());
        responseMessageList.add(new ResponseMessageBuilder().code(404).message("找不到资源").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").build());
        responseMessageList.add(new ResponseMessageBuilder().code(1001).message("参数错误").build());

        return new Docket(DocumentationType.SWAGGER_2)
            .globalResponseMessage(RequestMethod.POST,responseMessageList)
            .directModelSubstitute(Date.class, Long.class).directModelSubstitute(byte[].class, String.class)
            .apiInfo(apiInfo()).useDefaultResponseMessages(false).select()
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            .paths(PathSelectors.any()).build();

    }

    @Bean
    public  ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
//  mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        // 处理字节
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(byte[].class, getByteSerialize());
        mapper.registerModule(simpleModule);

        return mapper;
    }


    private JsonSerializer<byte[]> getByteSerialize() {
        return new JsonSerializer<byte[]>() {
			@Override
			public void serialize(byte[] value, JsonGenerator gen,
					SerializerProvider serializers) throws IOException,
					JsonProcessingException {
						if(null != value){
		                    gen.writeString(Base64.encodeToString(value));
		                }else{
		                    gen.writeString("");
		                }
				
			}
        };
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("医保监管平台")
            .description("医保监管平台API接口")
            .termsOfServiceUrl("www.a-eye.cn")
            .version("1.0.0")
            .build();
    }


}