package com.pjf.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pjf.server.entity.Menu;
import com.pjf.server.entity.MenuRole;
import com.pjf.server.entity.Role;
import com.pjf.server.service.IMenuRoleService;
import com.pjf.server.service.IMenuService;
import com.pjf.server.service.IRoleService;
import com.pjf.server.utils.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
@RestController
@Tag(name = "角色管理")
@SecurityRequirement(name = "Authorization")
@RequestMapping("/sprout/system/basic/premise")
public class RoleController {
    @Resource
    private IRoleService roleService;
    @Resource
    private IMenuService menuService;
    @Resource
    private IMenuRoleService menuRoleService;

    @Operation(summary = "获取所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @Operation(summary = "添加角色")
    @PostMapping("/role")
    public ApiResult addRole(@RequestBody Role role) {
        String ROLE = "ROLE_";
        if (!role.getName().startsWith(ROLE)) {
            role.setName(ROLE + role.getName());
        }
        if (roleService.save(role)) {
            return ApiResult.success("添加成功");
        }
        return ApiResult.error("添加失败");
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/role/{rid}")
    public ApiResult deleteRole(@PathVariable Integer rid) {
        if (roleService.removeById(rid)) {
            return ApiResult.success("删除成功");
        }
        return ApiResult.error("删除失败");
    }

    @Operation(summary = "根据角色Id查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid) {
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("role_id", rid)).
                stream().map(MenuRole::getMenuId).collect(Collectors.toList());
    }

    @Operation(summary = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @Operation(summary = "更新角色菜单")
    @PutMapping("/")
    public ApiResult updateMenuRole(Integer rid, Integer[] ids) {
        return menuRoleService.updateMenuRole(rid, ids);
    }
}
