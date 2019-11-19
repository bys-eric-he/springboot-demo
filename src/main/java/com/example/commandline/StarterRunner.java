package com.example.commandline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

/**
 * 实现自定义Starter
 */
@Order(value = 2)
public class StarterRunner implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(StarterRunner.class);

    @Override
    public void run(String... strings) throws Exception {
        logger.info("-------------------自定义Starter运行啦---------------");
    }
}
