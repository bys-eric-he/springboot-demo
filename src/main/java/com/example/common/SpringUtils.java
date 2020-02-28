package com.example.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 不知道大家有没有试过在普通Bean中注入ApplicationContext实例？你第一时间想到的是：
 * * *@Autowired
 * * *ApplicationContext applicationContext;
 * * 除了利用Spring本身的IOC容器自动注入以外，你还有别的办法吗？
 * * 我们可以让Bean实现ApplicationContextAware接口。
 * * 后期Spring会调用setApplicationContext()方法传入ApplicationContext实例。
 * *
 */
@Component
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    /**
     * 这是我认为Spring最牛逼的地方：代码具有高度的可扩展性，甚至你自己都懵逼，
     * 为什么实现了一个接口，这个方法就被莫名其妙调用，还传进了一个对象…
     *
     * @param arg0
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = arg0;
        }
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
