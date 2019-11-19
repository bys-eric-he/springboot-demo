package com.example;

import com.example.exception.BusinessException;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * 捕获异常统一处理
 */
@ControllerAdvice
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final static String EXPTION_MSG_KEY = "message";
    private final static String OK_MESSAGE = "{\"code\":0,\"data\":%s}";

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ErrorMessage handleBizExp(HttpServletRequest request, Exception ex) {
        logger.info("Business exception handler  " + ex.getMessage());
        //request.getSession(true)：若存在会话则返回该会话，否则新建一个会话
        //request.getSession(false)：若存在会话则返回该会话，否则返回NULL
        request.getSession(true).setAttribute(EXPTION_MSG_KEY, ex.getMessage());

        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            return new ErrorMessage(String.valueOf(businessException.getBizCode()), businessException.getMessage(), 1);
        } else {
            return new ErrorMessage("500", "系统错误,错误信息:" + ex.getMessage(), 1);
        }
    }

    @ExceptionHandler(SQLException.class)
    public ModelAndView handSql(Exception ex) {
        logger.info("SQL Exception " + ex.getMessage());
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", ex.getMessage());
        mv.setViewName("sql_error");
        return mv;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        String name = methodParameter.getMethod().getName();
        return !name.contains("uiConfiguration") && !name.contains("swaggerResources") && !name.contains("getDocumentation");
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        try {
            if (o instanceof ErrorMessage) {
                return o;
            }
            if (o instanceof String) {
                return String.format(OK_MESSAGE, o.toString());
            }
            if (o instanceof JsonNode) {
                return ResponseBase.ok(o.toString());
            }
            return ResponseBase.ok(o);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return o;
    }
}
