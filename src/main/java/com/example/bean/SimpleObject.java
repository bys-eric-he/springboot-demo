package com.example.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class SimpleObject {

    @Value("注入普通字符串")
    private String normal;

    /**
     * 注入表达式
     */
    @Value("#{1+2}")
    private double sum;

    /**
     * 注入系统变量属性
     */
    @Value("#{systemProperties['java.version']}")
    private String systemPropertiesName;

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 注入Car对象属性
     */
    @Value("#{car.number}")
    private String carNumber;

    /**
     * 注入文件资源
     */
    @Value("classpath:mail.properties")
    private Resource resourceFile;

    @Override
    public String toString() {
        return "SimpleObject{\n normal=" +
                normal + "\n resourceFile=" +
                resourceFile + "\n systemPropertiesName=" +
                systemPropertiesName + "\n applicationName=" +
                applicationName + "\n sum=" +
                sum + "\n serverPort=" +
                serverPort + "\n carNumber=" +
                carNumber +
                "}";
    }

}
