package com.example.docket;

import com.google.common.base.Predicate;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by eric on 2018/3/15.
 */
public abstract class SwaggerUIDocument extends Docket {
    public SwaggerUIDocument() {
        super(DocumentationType.SWAGGER_2);
        this.apiInfo(setApiInfo()).groupName(setGroupName())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(pathGroup())
                .build()
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .useDefaultResponseMessages(false)
                .globalOperationParameters(setParameters());
    }

    protected ApiInfo setApiInfo() {
        return new ApiInfoBuilder().title("SprintBoot Application")
                .description("该应用程序仅用于研究SprintBoot SprintMVC 等框架的使用")
                .version("1.0")
                .build();
    }

    protected abstract String setGroupName();

    protected Predicate<String> pathGroup() {
        return PathSelectors.any();
    }

    protected List<Parameter> setParameters() {
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder parameterBuilder = new ParameterBuilder()
                .name("Authorization")
                .description("身份信息")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(true);

        parameters.add(parameterBuilder.build());

        return parameters;
    }
}
