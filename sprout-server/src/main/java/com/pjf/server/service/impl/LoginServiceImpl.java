package com.pjf.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pjf.server.config.security.auth.PhoneAuthenticationToken;
import com.pjf.server.config.security.details.PhoneUserDetailService;
import com.pjf.server.config.security.details.UsernamePasswordUserDetailsService;
import com.pjf.server.entity.User;
import com.pjf.server.mapper.UserMapper;
import com.pjf.server.service.ILoginService;
import com.pjf.server.utils.ApiResult;
import com.pjf.server.utils.JwtTokenUtil;
import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.inmemory.request.InMemorySlidingWindowRequestRateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author pjf
 * @since 2022/2/11 13:11
 * 登录service
 **/
@Slf4j
@Service
public class LoginServiceImpl implements ILoginService {
    //规则定义：1小时之内5次机会，第6次失败就触发限流行为（禁止访问）
    Set<RequestLimitRule> rules =
            Collections.singleton(RequestLimitRule.of(Duration.ofMinutes(60), 5));
    RequestRateLimiter limiter = new InMemorySlidingWindowRequestRateLimiter(rules);
    @Resource
    private UserMapper userMapper;
    @Resource
    private UsernamePasswordUserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private PhoneUserDetailService pUserDetailService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param request  客户端请求
     * @return 返回登录结果以及token
     */
    @Override
    public ApiResult login(String username, String password, String code, HttpServletRequest request) {
        //TODO 添加验证码
        //根据账号加载用户
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //判断是否有该用户，以及密码是否匹配
        if (null == userDetails || !passwordEncoder.matches(password, userDetails.getPassword())) {
            String errorMsg = "用户名或密码错误！请重新输入";
            //每次登陆失败计数器加1，并判断该用户是否已经到了触发了锁定规则
            boolean reachLimit = limiter.overLimitWhenIncremented(username);
            if (reachLimit) { //如果触发了锁定规则，修改数据库 accountNonLocked字段锁定用户
                assert userDetails != null;
                User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", userDetails.getUsername()));
                userMapper.updateLock(user.getId(), !user.isAccountNonLocked());
                errorMsg = "您多次登陆失败，账户已被锁定，请稍后再试！";
            }

            return ApiResult.error(errorMsg);
        }
        if (!userDetails.isAccountNonLocked()) {
            return ApiResult.error("您已经多次登陆失败，账户已被锁定，请稍后再试");
        }
        //判断是否被禁用（封号）
        if (!userDetails.isEnabled()) {
            return ApiResult.error("该账号已被禁用，请联系管理员！");
        }
        //创建用户名密码身份验证令牌
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        Map<String, String> token = getToken(authenticationToken, userDetails);
        return ApiResult.success("登录成功", token);
    }

    @Override
    public ApiResult loginPhone(String phone, String code, HttpServletRequest request) {
        String captcha = redisTemplate.opsForValue().get(phone);
        if (!code.equals(captcha)) {
            return ApiResult.error("登录失败，验证码错误");
        }
        UserDetails userDetails = pUserDetailService.loadUserByUsername(phone);
        //判断是否被禁用（封号）
        if (!userDetails.isEnabled()) {
            return ApiResult.error("该账号已被禁用，请联系管理员！");
        }
        if (!userDetails.isAccountNonExpired()) {
            return ApiResult.error("账户已被锁定，请稍后再试，或练习管理员");
        }
        //创建用户名密码身份验证令牌
        PhoneAuthenticationToken phoneToken = new PhoneAuthenticationToken(userDetails.getAuthorities(), phone, code);
        Map<String, String> token = getToken(phoneToken, userDetails);
        return ApiResult.success("登录成功", token);
    }


    /**
     * 注册
     *
     * @param user 注册信息
     * @param code 注册信息
     * @return 返回注册结果
     */
    @Override
    public ApiResult register(User user, String code) {
        //TODO 注册时验证验证码
        User dbUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (dbUser != null) {
            return ApiResult.error("用户已存在，请登录或重新注册！");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setCreateDate(LocalDate.now());
        log.error(user.toString());
        int success = userMapper.insert(user);
        if (success > 0) {
            return ApiResult.success("注册成功");
        }
        return ApiResult.error("注册失败，请重新注册！");
    }

    private Map<String, String> getToken(Authentication authentication, UserDetails userDetails) {
        //设置用户登录
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //根据用户名密码生成Token
        String token = jwtTokenUtil.generateToken(userDetails);
        //要返回前端的数据 用户信息
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return tokenMap;
    }
}
