package com.example.config;

import com.example.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportCarConfig {

    @Bean("car")
    public Car configCar() {
        return new Car();
    }
}
