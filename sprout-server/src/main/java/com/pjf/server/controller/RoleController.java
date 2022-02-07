package com.pjf.server.controller;


import com.pjf.server.entity.Role;
import com.pjf.server.service.IRoleService;
import com.pjf.server.utils.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;

    @Operation(summary = "获取所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @Operation(summary = "添加角色")
    @PostMapping("/")
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
    @DeleteMapping("/{rid}")
    public ApiResult deleteRole(@PathVariable Integer rid) {
        if (roleService.removeById(rid)) {
            return ApiResult.success("删除成功");
        }
        return ApiResult.error("删除失败");
    }

}
