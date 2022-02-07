package com.pjf.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pjf.server.entity.MenuRole;
import com.pjf.server.mapper.MenuRoleMapper;
import com.pjf.server.service.IMenuRoleService;
import com.pjf.server.utils.ApiResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Resource
    private MenuRoleMapper menuRoleMapper;


    @Override
    @Transactional
    public ApiResult updateMenuRole(Integer rid, Integer[] ids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        if (null == ids || 0 == ids.length) {
            return ApiResult.success("更新成功!");
        }
        Integer count = menuRoleMapper.insertReport(ids, rid);
        if (count == ids.length) {
            return ApiResult.success("更新成功");
        }
        return ApiResult.error("更新失败");
    }
}
