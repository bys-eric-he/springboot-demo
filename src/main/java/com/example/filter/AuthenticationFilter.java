package com.example.filter;

import com.example.authentication.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 在HttpServletRequest 到达Servlet 之前，拦截客户的HttpServletRequest.
 */
@WebFilter(filterName = "authFilter", urlPatterns = "/api/*")
@Order(0)
public class AuthenticationFilter extends OncePerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 不过滤的uri
        String[] notFilter = new String[]{"/login/","/v2/api-docs"};

        // 请求的uri
        String uri = request.getRequestURI();

        logger.info("AuthenticationFilter过虑到的请求->{}", uri);

        // uri中包含api时才进行过滤
        if (uri.contains("api")) {
            // 是否过滤
            boolean doFilter = true;
            for (String s : notFilter) {
                if (uri.contains(s)) {
                    // 如果uri中包含不过滤的uri，则不进行过滤
                    doFilter = false;
                    break;
                }
            }
            if (doFilter) {
                // 执行过滤
                // 从session中获取登录者实体
                Object obj = request.getSession().getAttribute("token");
                // 从Header中获取登录者的身份信息
                Object authorization = request.getHeader("Authorization");
                if (null == obj && authorization == null) {
                    // 如果session中不存在登录者实体，则弹出框提示重新登录
                    // 设置request和response的字符集，防止乱码
                    request.setCharacterEncoding("UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    String loginPage = "....";
                    String builder = "<script type=\"text/javascript\">" +
                            "alert('网页过期，请重新登录！');" +
                            "window.top.location.href='" +
                            loginPage +
                            "';" +
                            "</script>";
                    out.print(builder);
                } else {
                    Authentication authentication = new AuthenticationToken(authorization);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    // 如果session中存在登录者实体，则继续
                    filterChain.doFilter(request, response);
                }
            } else {
                // 如果不执行过滤，则继续
                filterChain.doFilter(request, response);
            }
        } else {
            // 如果uri中不包含background，则继续
            filterChain.doFilter(request, response);
        }
    }
}