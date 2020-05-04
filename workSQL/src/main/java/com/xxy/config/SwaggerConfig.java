package com.xxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration //配置类
@EnableSwagger2// 开启Swagger2的自动配置
public class SwaggerConfig {

    @Bean //配置docket以配置Swagger具体参数
    public Docket docket() {
        return  new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
        //any() // 扫描所有，项目中的所有接口都会被扫描到
        //none() // 不扫描接口
        // 通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
        //withMethodAnnotation(final Class<? extends Annotation> annotation)
        // 通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
        //withClassAnnotation(final Class<? extends Annotation> annotation)
        //basePackage(final String basePackage) // 根据包路径扫描接口
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xxy.controller"))
                .build() ;
    }

    private ApiInfo apiInfo(){
        Contact contact= new Contact("xxy","http://baidu.com","729570443@qq.com");

        return new ApiInfo(
            "xxy Documentation",
            "最亮的星星",
            "V1.0",
             "http://www.baidu.com",
            contact,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList() );
    }

}
