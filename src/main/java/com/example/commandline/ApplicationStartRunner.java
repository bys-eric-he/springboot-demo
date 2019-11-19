package com.example.commandline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class ApplicationStartRunner implements ApplicationRunner {
    private static Logger logger = LoggerFactory.getLogger(ApplicationStartRunner.class);
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger.info("------------------->自定义ApplicationStartRunner运行啦<---------------");
    }
}
