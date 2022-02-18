package com.pjf.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pjf.server.entity.Role;
import com.pjf.server.entity.User;
import com.pjf.server.utils.ApiResult;
import org.springframework.security.core.Authentication;

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
     * 根据账号获取用户信息
     *
     * @param username 账号
     * @return 返回用户信息
     */
    User getUserByUserName(String username);

    /**
     * 根据用户ID获取角色列表
     *
     * @param id 用户ID
     * @return 角色列表
     */
    List<Role> getRoles(Integer id);

    /**
     * 修改密码
     *
     * @param oldPass 老密码
     * @param pass    新密码
     * @param userId  用户id
     * @return 返回修改结果
     */
    ApiResult updateUserPassword(String oldPass, String pass, Integer userId);

    /**
     * 更新用户头像
     *
     * @param url            图片地址
     * @param id             用户id
     * @param authentication 权限
     * @return 返回更新结果
     */
    ApiResult updateUserUserFace(String url, Integer id, Authentication authentication);

    /**
     * 获取用户列表
     *
     * @return 返回用户列表
     */
    List<User> getAllUsers();

    /**
     * 根据手机号查询用户信息
     *
     * @param phone 手机号
     * @return 返回用户信息
     */
    User getUserByPhone(String phone);


    /**
     * 更新用户状态
     *
     * @param id 用户id
     * @param b  解除锁定
     * @return 返回解除锁定结果
     */
    boolean updateLock(Integer id, boolean b);

    /**
     * 更新启用状态
     *
     * @param id 用户Id
     * @param b  是否启用
     * @return 返回启用结果
     */
    boolean updateEnabled(Integer id, boolean b);
}
