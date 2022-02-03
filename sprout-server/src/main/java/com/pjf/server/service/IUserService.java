package com.pjf.server.service;

import com.pjf.server.entity.Role;
import com.pjf.server.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pjf.server.entity.UserLogin;
import com.pjf.server.utils.ApiResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param request  客户端请求
     * @return 返回登录结果以及token
     */
    ApiResult login(String username, String password, String code, HttpServletRequest request);

    /**
     * 注册
     *
     * @param user 注册信息
     * @param code 验证码
     * @return 返回注册结果
     */
    ApiResult register(User user, String code);

    /**
     * 根据账号获取用户信息
     *
     * @param username 账号
     * @return 返回用户信息
     */
    User getAdminByUserName(String username);

    /**
     * 根据用户ID获取角色列表
     *
     * @param id 用户ID
     * @return 角色列表
     */
    List<Role> getRoles(Integer id);
}
