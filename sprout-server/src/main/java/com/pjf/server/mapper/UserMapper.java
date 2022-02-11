package com.pjf.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pjf.server.entity.Role;
import com.pjf.server.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户ID获取角色列表
     *
     * @param id 用户ID
     * @return 角色列表
     */
    List<Role> getRoles(Integer id);

    /**
     * 获取用户列表
     *
     * @return 返回用户列表
     */
    List<User> getAllUsers();
}
