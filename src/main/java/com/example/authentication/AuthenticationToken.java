package com.example.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.security.auth.Subject;
import java.util.Collections;

/**
 * Created by eric on 2018/3/15.
 */
public class AuthenticationToken extends AbstractAuthenticationToken {

    private Object authorization;

    public AuthenticationToken(Object authorization) {
        super(Collections.emptyList());
        this.authorization = authorization;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.authorization;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

    /**
     * 获取当前用户
     * SecurityContext存放在ThreadLocal中的,
     * 通过ThreadLocal.set() 到线程中的对象是该线程自己使用的对象，
     * 其他线程是不需要访问的，也访问不到的。各个线程中访问的是不同的对象。
     * 每次权限鉴定的时候都是从ThreadLocal中获取SecurityContext中对应的Authentication所拥有的权限.
     *
     * @return
     */
    public static Object getCurrentUser() {

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
