package com.pjf.server.config.security.adaper;

import com.pjf.server.config.security.details.PhoneUserDetailService;
import com.pjf.server.config.security.filter.PhoneAuthenticationFilter;
import com.pjf.server.config.security.provider.PhoneAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author pjf
 * @since 2022/2/10 16:31
 * 配置
 **/
@Configuration
public class PhoneSecurityConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private PhoneUserDetailService userDetailService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        PhoneAuthenticationFilter phoneAuthenticationFilter = new PhoneAuthenticationFilter();
        phoneAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        PhoneAuthenticationProvider authenticationProvider = new PhoneAuthenticationProvider(userDetailService, redisTemplate);
        http.authenticationProvider(authenticationProvider).addFilterAfter(phoneAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
