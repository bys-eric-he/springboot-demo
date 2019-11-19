package com.example.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(filterName = "actionFilter",  urlPatterns = "/api/*")
@Order(1)
public class ActionFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(ActionFilter.class);

    /**
     * 封装，不需要过滤的list列表
     */
    private static List<Pattern> patterns = new ArrayList<Pattern>();

    @PostConstruct
    public void initPatterns(){
        patterns.add(Pattern.compile(".*swagger.*"));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("ActionFilter过滤器初始化...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        logger.info("ActionFilter过滤到的请求->{},执行过滤操作.", url);
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }

        if (isInclude(url)) {
            chain.doFilter(httpRequest, httpResponse);
        } else {
            HttpSession session = httpRequest.getSession();
            if (session.getAttribute("") != null) {
                // session存在
                chain.doFilter(httpRequest, httpResponse);
            } else {
                // session不存在 准备跳转失败
                /* RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                    dispatcher.forward(request, response);*/
                chain.doFilter(httpRequest, httpResponse);
            }
        }
    }

    @Override
    public void destroy() {
        logger.info("ActionFilter过滤器销毁...");
    }

    /**
     * 是否需要过滤
     *
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}