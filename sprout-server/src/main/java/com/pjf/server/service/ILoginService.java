package com.pjf.server.service;

import com.pjf.server.entity.User;
import com.pjf.server.utils.ApiResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pjf
 * @since 2022/2/11 13:10
 * 登录接口
 **/
public interface ILoginService {
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
     * 手机号登录
     *
     * @param phone   手机号
     * @param code    验证码
     * @param request request
     * @return 返回Token
     */
    ApiResult loginPhone(String phone, String code, HttpServletRequest request);

    /**
     * 注册
     *
     * @param user 注册信息
     * @param code 验证码
     * @return 返回注册结果
     */
    ApiResult register(User user, String code);
}
