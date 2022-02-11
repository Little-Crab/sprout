package com.pjf.server.config.security.provider;

import com.pjf.server.config.security.auth.PhoneAuthenticationToken;
import com.pjf.server.config.security.details.PhoneUserDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author pjf
 * @since 2022/2/10 15:08
 * 认证
 **/
@Component
public class PhoneAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private PhoneUserDetailService userDetailService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public PhoneAuthenticationProvider(PhoneUserDetailService userDetailService, RedisTemplate<String, String> redisTemplate) {
        this.userDetailService = userDetailService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PhoneAuthenticationToken authenticationToken = (PhoneAuthenticationToken) authentication;
        Object principal = authenticationToken.getPrincipal();
        String phone = "";
        if (principal instanceof String) {
            phone = (String) principal;
        }
        Object captcha = authenticationToken.getCredentials();
        String redisCaptcha = redisTemplate.opsForValue().get(phone);
        if (StringUtils.isEmpty(redisCaptcha)) {
            throw new BadCredentialsException("验证码过期或未发送，请重新获取验证码");
        }
        if (!captcha.equals(redisCaptcha)) {
            throw new BadCredentialsException("验证码错误，请重新输入验证码");
        }
        UserDetails userDetails = userDetailService.loadUserByUsername(phone);
        if (null == userDetails) {
            throw new InternalAuthenticationServiceException(phone + "用户不存在，请注册");
        }
        PhoneAuthenticationToken authenticationTokenResult = new PhoneAuthenticationToken(userDetails.getAuthorities(), principal, captcha);
        authenticationTokenResult.setDetails(authenticationToken.getDetails());
        return authenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PhoneAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
