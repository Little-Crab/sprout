package com.pjf.server.config.security.filter;

import com.pjf.server.config.security.auth.PhoneAuthenticationToken;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pjf
 * @since 2022/2/10 14:31
 * 过滤器 手机号登录过滤器
 **/
public class PhoneAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    // 设置拦截/login/phone短信登录接口
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login/phone", "POST");
    // 认证参数
    private String phoneParameter = "phone";
    private String captchaParameter = "phoneCaptcha";
    private boolean postOnly = true;

    public PhoneAuthenticationFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    public PhoneAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String phone = this.obtainPhone(request);
            phone = phone != null ? phone : "";
            phone = phone.trim();
            String phneCode = this.obtainCaptcha(request);
            phneCode = phneCode != null ? phneCode : "";
            PhoneAuthenticationToken authRequest = new PhoneAuthenticationToken(phone, phneCode);
            // 认证
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    @Nullable
    public String obtainPhone(HttpServletRequest request) {
        return request.getParameter(phoneParameter);
    }

    @Nullable
    public String obtainCaptcha(HttpServletRequest request) {
        return request.getParameter(captchaParameter);
    }

    protected void setDetails(HttpServletRequest request, PhoneAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    //为请求方式赋值
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    //获取手机号
    public String getPhoneParameter() {
        return this.phoneParameter;
    }

    //为手机号赋值
    public void setPhoneParameter(String phoneParameter) {
        Assert.hasText(phoneParameter, "手机号不能为空");
        this.phoneParameter = phoneParameter;
    }

    //获取手机验证码
    public String getCaptchaParameter() {
        return this.captchaParameter;
    }

    //为手机验证码赋值
    public void setCaptchaParameter(String captchaParameter) {
        Assert.hasText(captchaParameter, "手机验证码不能为空");
        this.captchaParameter = captchaParameter;
    }
}
