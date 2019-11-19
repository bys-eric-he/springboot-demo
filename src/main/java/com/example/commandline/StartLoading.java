package com.example.commandline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class StartLoading implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(StartLoading.class);

    @Override
    public void run(String... strings) throws Exception {
        logger.info("---------StartLoading CommandLineRunner Order=1---------------");
    }
}
