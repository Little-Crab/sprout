package com.pjf.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pjf.server.entity.Menu;
import com.pjf.server.mapper.MenuMapper;
import com.pjf.server.service.IMenuService;
import com.pjf.server.utils.UserUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据用户ID查询菜单列表
     *
     * @return 返回菜单列表
     */
    @Override
    public List<Menu> getMenusByUserId() {
        Integer adminId = UserUtils.getCurrentUser().getId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //从redis获取菜单数据
        @SuppressWarnings("unchecked")
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
        //如果为空从数据库获取
        if (CollectionUtils.isEmpty(menus)) {
            menus = menuMapper.getMenusByUserId(adminId);
            valueOperations.set("menu_" + adminId, menus);
        }
        return menus;
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
