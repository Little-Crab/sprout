package com.pjf.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pjf.server.entity.Menu;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户ID查询菜单列表
     *
     * @param id 用户ID
     * @return 返回菜单列表
     */
    List<Menu> getMenusByUserId(Integer id);

    /**
     * 获取所有菜单
     *
     * @return 返回菜单列表
     */
    List<Menu> getAllMenus();
}
