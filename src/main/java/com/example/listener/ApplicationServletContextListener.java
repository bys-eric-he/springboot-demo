package com.example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ServletContextListener 是用于监听Web应用程序的启动和关闭
 */
@WebListener
public class ApplicationServletContextListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(ApplicationServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("SpringBootDemo Web应用程序启动 初始化...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("pringBootDemo Web应用程序启动 销毁...");
    }
}