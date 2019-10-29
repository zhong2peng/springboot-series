package com.dnight.activiti.config;

import org.springframework.beans.factory.annotation.Value;
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

/**
 * @author ZHONGPENG769
 * @date 2019/10/29
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value(value = "${swagger.enabled:false}")
    Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled).select()
                .apis(RequestHandlerSelectors.basePackage("com.dnight.activiti"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("工作流API")
                .description("搬砖使我快乐")
                .contact(new Contact("dnight", "http://ivfreedom.com", "2458178760@qq.com"))
                .version("1.0.0")
                .build();
    }
}
