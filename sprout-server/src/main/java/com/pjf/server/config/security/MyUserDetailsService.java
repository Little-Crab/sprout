package com.pjf.server.config.security;

import com.pjf.server.entity.User;
import com.pjf.server.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author pjf
 * @since 2022/2/6 23:20
 * 自定义userDetailsService
 **/
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getAdminByUserName(username);
        if (null != user) {
            user.setRoles(userService.getRoles(user.getId()));
            return user;
        }
        throw new UsernameNotFoundException("用户名或密码不正确!!!");
    }
}
