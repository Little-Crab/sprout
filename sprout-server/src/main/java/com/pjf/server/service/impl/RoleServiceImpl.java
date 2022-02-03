package com.pjf.server.service.impl;

import com.pjf.server.entity.Role;
import com.pjf.server.mapper.RoleMapper;
import com.pjf.server.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
