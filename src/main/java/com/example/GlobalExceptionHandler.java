package com.example;

import com.example.exception.BusinessException;
import com.example.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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

    @ExceptionHandler({BusinessException.class, Exception.class})
    @ResponseBody
    public ErrorResult handleBizExp(HttpServletRequest request, Exception ex) {
        logger.info("Business exception handler  " + ex.getMessage());
        //request.getSession(true)：若存在会话则返回该会话，否则新建一个会话
        //request.getSession(false)：若存在会话则返回该会话，否则返回NULL
        request.getSession(true).setAttribute(EXPTION_MSG_KEY, ex.getMessage());

        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            return new ErrorResult(String.valueOf(businessException.getBizCode()), businessException.getMessage(), true);
        } else {
            return new ErrorResult(ResultCode.FAILED);
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

        //检查是否请求了带@ResponseResult注解标记的API，有就重写返回体，没有就直接返回。
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        ResponseResult responseResult = (ResponseResult) request.getAttribute(ConstantInterface.RESPONSE_RESULT_NN);
        return responseResult != null;
        /*
        String name = methodParameter.getMethod().getName();
        return !name.contains("uiConfiguration") && !name.contains("swaggerResources") && !name.contains("getDocumentation");
        */
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        try {
            logger.info("response body write process...");
            if (body instanceof ErrorResult) {
                ErrorResult errorResult = (ErrorResult) body;

                return CommonResult.failed(errorResult.getMsg());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            //ResultCode resultCode = ResultCode.FAILED;
            return CommonResult.failed(exception.getMessage());
        }

        return CommonResult.success(body);
    }
}
