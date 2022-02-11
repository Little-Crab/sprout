package com.pjf.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pjf.server.config.security.auth.PhoneAuthenticationToken;
import com.pjf.server.config.security.details.PhoneUserDetailService;
import com.pjf.server.entity.Role;
import com.pjf.server.entity.User;
import com.pjf.server.mapper.UserMapper;
import com.pjf.server.service.IUserService;
import com.pjf.server.utils.ApiResult;
import com.pjf.server.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserDetailsService userDetailsService;
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
            return ApiResult.error("用户名或密码错误！请重新输入！");
        }
        //判断是否被禁用（封号）
        if (!userDetails.isEnabled()) {
            return ApiResult.error("该账号已被禁用，请联系管理员！");
        }
        //创建用户名密码身份验证令牌
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        //设置用户登录
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //根据用户名密码生成Token
        String token = jwtTokenUtil.generateToken(userDetails);
        //要返回前端的数据 用户信息
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return ApiResult.success("登录成功", tokenMap);
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

    /**
     * 根据账号获取用户信息
     *
     * @param username 账号
     * @return 返回用户信息
     */
    @Override
    public User getUserByUserName(String username) {

        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username).eq("enabled", true));

    }

    /**
     * 根据用户ID获取角色列表
     *
     * @param id 用户ID
     * @return 角色列表
     */
    @Override
    public List<Role> getRoles(Integer id) {
        return userMapper.getRoles(id);
    }

    /**
     * 修改密码
     *
     * @param oldPass 老密码
     * @param pass    新密码
     * @param userId  用户id
     * @return 返回修改结果
     */
    @Override
    public ApiResult updateUserPassword(String oldPass, String pass, Integer userId) {
        User admin = userMapper.selectById(userId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //判断旧密码是否正确
        if (encoder.matches(oldPass, admin.getPassword())) {
            admin.setPassword(encoder.encode(pass));
            int i = userMapper.updateById(admin);
            if (1 == i) {
                return ApiResult.success("更新成功");
            }
        }
        return ApiResult.error("更新失败");
    }

    @Override
    public ApiResult updateUserUserFace(String url, Integer id, Authentication authentication) {
        User user = userMapper.selectById(id);
        user.setImgUrl(url);
        int result = userMapper.updateById(user);
        if (1 == result) {
            User principal = (User) authentication.getPrincipal();
            principal.setImgUrl(url);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, authentication.getAuthorities()));
            return ApiResult.success("更新成功", url);
        }
        return ApiResult.error("更新失败");
    }

    /**
     * 获取用户列表
     *
     * @return 返回用户列表
     */
    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    /**
     * 根据手机号查询用户信息
     *
     * @param phone 手机号
     * @return 返回用户信息
     */
    @Override
    public User getUserByPhone(String phone) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("phone", phone));
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
        //创建用户名密码身份验证令牌
        PhoneAuthenticationToken phoneToken = new PhoneAuthenticationToken(userDetails.getAuthorities(), phone, code);
        //设置用户登录
        SecurityContextHolder.getContext().setAuthentication(phoneToken);
        //根据用户名密码生成Token
        String token = jwtTokenUtil.generateToken(userDetails);
        //要返回前端的数据 用户信息
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return ApiResult.success("登录成功", tokenMap);
    }
}
