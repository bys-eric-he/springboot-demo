package com.example.config;

import com.example.commandline.StarterRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarterAutoConfig {

    @Bean
    public StarterRunner getStarterRunner() {
        return new StarterRunner();
    }
}
