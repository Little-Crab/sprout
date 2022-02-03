package com.pjf.server.mapper;

import com.pjf.server.entity.Role;
import com.pjf.server.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
}
