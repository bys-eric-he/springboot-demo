package com.example.authentication;

import com.example.security.UserSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 验证类
 */
@Component
public class AuthenticationTokenProvider implements AuthenticationProvider {

    private Logger logger = LoggerFactory.getLogger(AuthenticationTokenProvider.class);

    @Autowired
    private UserSecurityService userSecurityService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //authentication.getPrincipal()调用的是AuthenticationToken类中重写的getPrincipal()方法
        String token = (authentication.getPrincipal() == null) ? null : authentication.getPrincipal().toString();
        if (StringUtils.isEmpty(token)) {
            throw new BadCredentialsException("AuthenticationToken对象token不能为空！");
        }

        UserDetails userDetails = userSecurityService.loadUserByUsername(token);

        logger.info("AuthenticationTokenProvider-> userName:" + userDetails.getUsername() + "password:" + userDetails.getPassword());
        //这里也可以再从数据库中去查询,为了简单起见,直接返回当前线程的身份信息
        return AuthenticationToken.getCurrentAuthentication();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        logger.info(this.getClass().getName() + "---supports");
        return (AuthenticationToken.class.isAssignableFrom(authentication));
    }
}