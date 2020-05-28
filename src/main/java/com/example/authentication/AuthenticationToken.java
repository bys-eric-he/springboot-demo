package com.example.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by eric on 2018/3/15.
 * 生成登录session，同用户不用再校验
 */
public class AuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private Object principal;
    private String credentials;

    public AuthenticationToken(Object principal) {
        super(Collections.emptyList());
        this.principal = principal;
    }

    public AuthenticationToken(Object principal, String credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    public AuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = null;
        // must use super, as we override
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }

    /**
     * 获取当前用户
     * SecurityContext存放在ThreadLocal中的,
     * 通过ThreadLocal.set() 到线程中的对象是该线程自己使用的对象,换句话说就是哪个线程存进来的,哪个线程才能访问
     * 其他线程是不需要访问的,也访问不到的,各个线程中访问的是不同的对象。
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

    /**
     * 获取当前用户Authentication对象
     * SecurityContext存放在ThreadLocal中的,
     * 通过ThreadLocal.set() 到线程中的对象是该线程自己使用的对象,换句话说就是哪个线程存进来的,哪个线程才能访问
     * 其他线程是不需要访问的,也访问不到的,各个线程中访问的是不同的对象。
     * 每次权限鉴定的时候都是从ThreadLocal中获取SecurityContext中对应的Authentication所拥有的权限.
     *
     * @return
     */
    public static Authentication getCurrentAuthentication() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
