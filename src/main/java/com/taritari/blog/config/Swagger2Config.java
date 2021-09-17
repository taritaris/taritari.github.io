package com.taritari.blog.config;

import io.swagger.annotations.Api;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Documentation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @ClassName Swagger2Config
 * @Description TODOShiroConfig.java
 * @date 2021/9/17 10:47
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    //    Swagger实例Bean是Docket，所以通过配置Docket实例来配置Swaggger。
    @Bean //配置docket以配置Swagger具体参数
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("taritari")
                .apiInfo(apiInfo())
                // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.taritari.blog.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    //配置文档信息
    private ApiInfo apiInfo(){
        Contact contact = new Contact("taritari", "https://space.bilibili.com/8693918", "1150082783@qq.com");
        return new ApiInfo(
                "Vue-SpringBoot-Blog", // 标题
                "Personal Blog @HuChao", // 描述
                "v2.0", // 版本
                "https://www.51moot.net/main/index", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}
