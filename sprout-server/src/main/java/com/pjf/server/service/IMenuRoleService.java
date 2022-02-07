package com.pjf.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pjf.server.entity.MenuRole;
import com.pjf.server.utils.ApiResult;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     *
     * @param rid 角色id
     * @param ids 菜单ids
     * @return 返回
     */
    ApiResult updateMenuRole(Integer rid, Integer[] ids);
}
