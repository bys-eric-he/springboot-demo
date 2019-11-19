package com.example.config;

import com.example.bean.House;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportHouseConfig {

    @Bean("house")
    public House houseConfig() {
        return new House();
    }
}
