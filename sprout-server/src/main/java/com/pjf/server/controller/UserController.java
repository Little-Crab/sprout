package com.pjf.server.controller;


import com.pjf.server.entity.User;
import com.pjf.server.service.IUserService;
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
@Tag(name = "用户管理", description = "主要为用户信息的删除，和查看")
@RequestMapping("/system/user")
@SecurityRequirement(name = "Authorization")
public class UserController {
    @Resource
    private IUserService userService;

    @Operation(summary = "获取用户列表")
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
