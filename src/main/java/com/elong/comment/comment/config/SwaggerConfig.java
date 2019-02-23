package com.elong.comment.comment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("elong comment")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.elong.comment.comment.controller"))
                .paths(PathSelectors.any())
//                .paths(PathSelectors.regex("/commet/.*"))
                .build();
    }

    /**
     * 项目信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger elong comment RESTful APIs") //大标题
                .description("about elong comment apis.") //小标题
                .version("1.0") //版本
                .termsOfServiceUrl("no terms of service")//服务条款
                .contact(new Contact("jack cao", "http://localhost:8080/swagger-ui.html", "xx"))//作者
                .license("free use")//链接显示文字
                .licenseUrl("http://localhost:8080/swagger-ui.html")//网站链接
                .build();
    }

}
