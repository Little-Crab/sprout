package com.pjf.server.config.security.details;

import com.pjf.server.config.security.exception.PhoneNotFoundException;
import com.pjf.server.entity.User;
import com.pjf.server.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author pjf
 * @since 2022/2/10 15:01
 * 手机号获取用户信息
 **/
@Component
public class PhoneUserDetailService implements UserDetailsService {
    @Resource
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userService.getUserByPhone(phone);
        if (null != user) {
            user.setRoles(userService.getRoles(user.getId()));
            return user;
        }
        throw new PhoneNotFoundException("手机号或验证码错误");
    }
}
