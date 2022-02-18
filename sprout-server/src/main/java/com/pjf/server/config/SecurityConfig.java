package com.pjf.server.config;

import com.pjf.server.config.security.adaper.PhoneSecurityConfigurerAdapter;
import com.pjf.server.config.security.details.UsernamePasswordUserDetailsService;
import com.pjf.server.config.security.filter.CustomFilter;
import com.pjf.server.config.security.filter.CustomUrlDecisionManager;
import com.pjf.server.config.security.filter.JwtAuthenticationTokenFilter;
import com.pjf.server.config.security.handler.RestAuthenticationEntryPoint;
import com.pjf.server.config.security.handler.RestfulAccessDeniedHandler;
import com.pjf.server.config.security.provider.PhoneAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * @author pjf
 * 类名：SecurityConfig
 * 创建时间: 2022/2/2 14:05.
 */
@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Resource
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Resource
    private CustomFilter customFilter;
    @Resource
    private CustomUrlDecisionManager urlDecisionManager;
    @Resource
    private PhoneSecurityConfigurerAdapter phoneSecurityConfigurerAdapter;
    @Resource
    private PhoneAuthenticationProvider phoneAuthenticationProvider;
    @Resource
    private UsernamePasswordUserDetailsService userDetailsService;
    @Resource
    private DataSource dataSource;

    /**
     * Token持久化对象
     *
     * @return 返回持久化对象
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/sprout/login",
                "/sprout/login/phone",
                "/sprout/register",
                "/sprout/logout",
                "/css/**",
                "/js/**",
                "/index",
                "favicon.ico",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v3/api-docs/**",
                "/sprout/captcha",
                "/sprout/captcha/**",
                "/ws/**",
                "/*"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //JWT不需要csrf
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //请求授权
                .authorizeRequests()
                //所有请求必须认证
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(urlDecisionManager);
                        o.setSecurityMetadataSource(customFilter);
                        return o;
                    }
                })
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository()) // 配置 token 持久化仓库
                .tokenValiditySeconds(3600) // remember 过期时间，单为秒
                .userDetailsService(userDetailsService) // 处理自动登录逻辑
                .and()
                .headers()
                //关闭缓存
                .cacheControl();

        //添加授权拦截器
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未登录或授权返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
        http.apply(phoneSecurityConfigurerAdapter);
    }

    @Override
    protected AuthenticationManager authenticationManager() {
        ProviderManager providerManager = new ProviderManager(List.of(phoneAuthenticationProvider));
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UsernamePasswordUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

}
