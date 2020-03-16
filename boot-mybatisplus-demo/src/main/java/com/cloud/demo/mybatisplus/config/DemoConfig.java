package com.cloud.demo.mybatisplus.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @ClassName DemoConfig
 * @Author JackZhou
 * @Date 2020/2/14  10:07
 **/

@Configuration
public class DemoConfig{


    @Value("${spring.swagger.package:com.cloud.demo}")
    private String swaggerPackage;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerPackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("spring-boot-swagger-demo")
                .description("这是一个简单的 Swagger API 演示")
                .version("1.0.0-SNAPSHOT")
                .build();
    }

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().addProperty("hibernate.validator.fail_fast", "true").buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    /**
     * @Author JackZhou
    mybatisplus 分頁
     **/
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

//    @Bean
//    public SqlExplainInterceptor sqlExplainInterceptor(){
//        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//        sqlParserList.add(new BlockAttackSqlParser());
//        sqlExplainInterceptor.setSqlParserList(sqlParserList);
//        return sqlExplainInterceptor;
//    }

    //    @Bean
//    //@ConditionalOnProperty(prefix = "spring.profiles", value = "active", havingValue = "dev")
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
//        performanceInterceptor.setMaxTime(1000);
//        /*<!--SQL是否格式化 默认false-->*/
//        performanceInterceptor.setFormat(true);
//
//        return performanceInterceptor;
//    }
}
