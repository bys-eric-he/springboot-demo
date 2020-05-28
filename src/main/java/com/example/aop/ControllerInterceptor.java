package com.example.aop;

import com.example.annotation.Permission;
import com.example.authentication.AuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Aspect
@Component
public class ControllerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 定义拦截规则：拦截com.example.controller包下面的所有类中，有@Log注解的方法。
     */
    @Pointcut("execution(* com.example.controller..*(..)) && @annotation(com.example.annotation.Log)")
    public void controllerMethodPointcut() {

    }

    /**
     * 使用@Before在切入点开始处切入内容
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("controllerMethodPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("进入@Before切面...");
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("TOKEN :" + AuthenticationToken.getCurrentUser());
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 拦截器具体实现 使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
     *
     * @param proceedingJoinPoint
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("controllerMethodPointcut()") //指定拦截器规则；也可以直接把“execution(* com.example.........)”写进这里
    public Object Interceptor(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        System.out.println("进入@Around切面...");
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名

        Set<Object> allParams = new LinkedHashSet<>(); //保存所有请求参数，用于输出到日志中

        logger.info("请求开始，方法：{}", methodName);

        Object result = null;

        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            //logger.debug("arg: {}", arg);
            if (arg instanceof Map<?, ?>) {
                //提取方法中的MAP参数，用于记录进日志中
                Map<String, Object> map = (Map<String, Object>) arg;

                allParams.add(map);
            } else if (arg instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) arg;
                if (isLoginRequired(method)) {
                    if (!isLogin(request)) {
                        result = "请先登录!";
                    }
                }

                //获取query string 或 posted form data参数
                Map<String, String[]> paramMap = request.getParameterMap();
                if (paramMap != null && paramMap.size() > 0) {
                    allParams.add(paramMap);
                }
            } else if (arg instanceof HttpServletResponse) {
                //do nothing...
            } else {
                //allParams.add(arg);
            }
        }

        logger.info("请求方法参数：{}", objectMapper.writeValueAsString(allParams));

        try {
            if (result == null) {
                // 一切正常的情况下，继续执行被拦截的方法
                result = proceedingJoinPoint.proceed();
            }
        } catch (Throwable e) {
            logger.info("exception: ", e);
            result = "操作异常!";
        }

        if (result != null) {
            long costMs = System.currentTimeMillis() - beginTime;
            logger.info("{}请求结束，耗时：{}ms", methodName, costMs);
        }

        return result;
    }

    /**
     * 使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "controllerMethodPointcut()")
    public void doAfterReturning(Object ret) throws Throwable {

        // 处理完请求，返回内容
        logger.info("进入@AfterReturning 切面, Response : " + objectMapper.writeValueAsString(ret));
    }

    /**
     * 判断一个方法是否需要登录
     *
     * @param method
     * @return
     */
    private boolean isLoginRequired(Method method) {
        boolean result = true;
        if (method.isAnnotationPresent(Permission.class)) {
            result = method.getAnnotation(Permission.class).loginReqired();
        }

        return result;
    }

    //判断是否已经登录
    private boolean isLogin(HttpServletRequest request) {
        return true;
    }
}
