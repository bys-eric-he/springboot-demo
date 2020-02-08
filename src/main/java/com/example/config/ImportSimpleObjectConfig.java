package com.example.config;

import com.example.bean.SimpleObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportSimpleObjectConfig {

    @Bean("simpleObject")
    public SimpleObject simpleObjectConfig(){
        return new SimpleObject();
    }
}
