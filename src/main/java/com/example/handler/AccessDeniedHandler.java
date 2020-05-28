package com.example.handler;

import com.alibaba.fastjson.JSON;
import com.example.response.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AccessDeniedHandler 异常处理
 */
public class AccessDeniedHandler implements AuthenticationEntryPoint {
    private static Logger logger = LoggerFactory.getLogger(AccessDeniedHandler.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(CommonResult.forbidden(authException.getMessage())));

        logger.warn("身份验证不通过!" + authException.getMessage());
    }
}