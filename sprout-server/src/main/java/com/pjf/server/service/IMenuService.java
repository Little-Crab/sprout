package com.pjf.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pjf.server.entity.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据用户ID查询菜单列表
     *
     * @return 返回菜单列表
     */
    List<Menu> getMenusByUserId();

    /**
     * 获取所有菜单
     *
     * @return 返回菜单列表
     */
    List<Menu> getAllMenus();
}
