package com.zyy.zyxk.web.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @Description swagger插件
 * @Author Yang.H
 * @Date 2021/6/2
 *
 **/
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@Profile({"produce","test","demo"})
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.zyy.zyxk.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("大地海洋-进销存管理系统 API")
                .description("大地海洋-进销存管理系统 API 1.0 操作文档")
                //服务条款网址
                .termsOfServiceUrl("http://www.hugehuge.cn")
                .version("1.0")
                .contact(new Contact("虎哥数字", "http://www.dhuge.com/", "it@zyy.cn"))
                .build();
    }
}
