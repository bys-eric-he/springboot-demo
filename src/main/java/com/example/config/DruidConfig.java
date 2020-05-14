package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 手写代码注册方法
 * 也可通过@WebFilter 、@WebServlet注解方式以及application.properties配置方式实现
 * 但三种方式只允许同时存在一种方式
 *
 * @Description: Druid监控配置
 * @Author: eric
 */
@Configuration
public class DruidConfig {

    /**
     * 配置读取spring数据源,扫描配置文件中前缀为spring.datasource.druid的配置信息,将数据源注册到配置容器中
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置Druid监控启动Servlet拦截
     */
    @Bean
    public ServletRegistrationBean druidStartViewServlet() {
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid" +
                "/*");

        //IP白名单（没有配置或者为空，则允许所有访问）
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //IP黑名单 (白名单和黑名单存在同一IP时，deny优先于allow),如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", "192.168.0.11");

        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "root");
        servletRegistrationBean.addInitParameter("loginPassword", "heyong1988");

        //是否能够重置数据,禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");

        return servletRegistrationBean;
    }

    /**
     * Druid监控过滤器配置规则
     */
    @Bean
    public FilterRegistrationBean druidStartFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

        //添加不需要忽略的格式信息,不拦截的静态资源.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        //启动COOKIE、SESSION监控
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        //添加过滤规则,拦截所有请求.
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}