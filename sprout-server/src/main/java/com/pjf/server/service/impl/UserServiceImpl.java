package com.pjf.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pjf.server.config.security.details.PhoneUserDetailService;
import com.pjf.server.config.security.details.UsernamePasswordUserDetailsService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public boolean updateLock(Integer id, boolean b) {
        return userMapper.updateLock(id, b) > 0;
    }

    @Override
    public boolean updateEnabled(Integer id, boolean b) {
        return userMapper.updateEnabled(id, b) > 0;
    }


}
