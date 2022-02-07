package com.pjf.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pjf.server.entity.MenuRole;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 更新角色菜单
     *
     * @param ids 菜单id
     * @param rid 角色id
     * @return 更新条数
     */
    Integer insertReport(Integer[] ids, Integer rid);
}
