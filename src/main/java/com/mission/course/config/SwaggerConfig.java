package com.mission.course.config;

import com.mg.swagger.framework.configuration.EnableSwaggerMgUi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author mission
 * @date 2018/10/5 0005-12:50
 */
@Configuration
@EnableSwagger2
@EnableSwaggerMgUi
public class SwaggerConfig {

  @Bean
  public Docket ProductApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .genericModelSubstitutes(DeferredResult.class)
        .useDefaultResponseMessages(false)
        .forCodeGeneration(false)
        .pathMapping("/")
        .select()
        .build()
        .apiInfo(productApiInfo());
  }

  private ApiInfo productApiInfo() {
    ApiInfo apiInfo =new ApiInfoBuilder()
        .title("广东海洋大学精品课程系统数据接口文档")
        .description("欢迎使用")
        .contact(new Contact("Missionary", "http://missionodd.coding.me/", "z1165996866@hotmail.com"))
        .version("v0.2.2")
        .licenseUrl("https://en.wikipedia.org/wiki/Copyleft")
        .license("Copyleft license 2018 by mission")
        .build();
    return apiInfo;
  }
}