package com.pjf.server.controller;


import com.pjf.server.entity.Menu;
import com.pjf.server.service.IMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Tag(name = "菜单管理")
@RequestMapping("/menu")
@SecurityRequirement(name = "Authorization")
public class MenuController {
    @Resource
    private IMenuService menuService;

    @Operation(summary = "根据用户ID查询菜单列表")
    @GetMapping("/")
    public List<Menu> getMenusByUserId() {
        return menuService.getMenusByUserId();
    }


}
