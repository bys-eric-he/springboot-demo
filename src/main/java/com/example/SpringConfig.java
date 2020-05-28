package com.example;

import com.example.entity.Counter;
import com.example.entity.Piano;
import com.example.filter.ActionFilter;
import com.example.filter.AuthFunctionInterceptor;
import com.example.filter.AuthenticationFilter;
import com.example.properties.UserProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.Filter;

/**
 * WebMvcConfigurerAdapter，这个类的作用是进行SpringMVC的一些配置
 * 等同于继承WebMvcConfigurationSupport, 如果项目中同时存在WebMvcConfigurationSupport的派生类时
 * WebMvcConfigurerAdapter 的派生类不会执行，优先使用WebMvcConfigurationSupport的派生类
 * 因为WebMvcConfigurerAdapter过期，使用WebMvcConfigurationSupport代替WebMvcConfigurerAdapter
 */
@Configuration
@EnableWebMvc
public class SpringConfig extends WebMvcConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(SpringConfig.class);

    /**
     * 如果直接用 @Value("${name}") 来取配置的值需要配置 PropertySourcesPlaceholderConfigurer 用来引入properties文件
     *
     * @return
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer() {
        ClassPathResource resource = new ClassPathResource("application.properties");
        PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocation(resource);
        return propertyPlaceholderConfigurer;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //当用户访问login时跳转到login.html页面
        registry.addViewController("/login").setViewName("login");
    }

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //初始化配置类中注册拦截器
        InterceptorRegistration addInterceptor = registry.addInterceptor(new AuthFunctionInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
        super.addInterceptors(registry);

        logger.info("SpringConfig 配置类中 添加拦截器 AuthFunctionInterceptor.....");
    }

    @Bean
    public FilterRegistrationBean authFilterRegistration() {
        //以下注册Filter的方式可使用注解@WebFilter代替
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(AuthFilter());
        registration.addUrlPatterns("/api/*");
        registration.setName("authFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean actionFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(ActionFilter());
        registration.addUrlPatterns("/api/*");
        registration.setName("actionFilter");
        registration.setOrder(2);
        return registration;
    }

    /*使用annotation tag来取代<bean></bean>*/
    @Bean
    public Filter AuthFilter() {
        return new AuthenticationFilter();
    }

    @Bean
    public Filter ActionFilter() {
        return new ActionFilter();
    }

    @Bean
    public Piano piano() {
        return new Piano();
    }

    @Bean(name = "counter")
    public Counter counter() {
        return new Counter(12, "Shake it Off", piano());
    }

    @Bean(name = "userProperties")
    public UserProperties userProperties() {
        return new UserProperties();
    }
}
