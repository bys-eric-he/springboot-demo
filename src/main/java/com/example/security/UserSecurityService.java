package com.example.security;

import com.example.entity.SysUser;
import com.example.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        System.out.println("parameter userName:"+userName);
        System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
        return user;
    }
}