package com.example.docket;

import org.springframework.context.annotation.Configuration;

/**
 * Created by eric on 2018/3/15.
 */
@Configuration
public class SprintBootSwaggerUIDocument extends SwaggerUIDocument {
    @Override
    protected String setGroupName() {
        return "Spring Boot应用程序";
    }
}
