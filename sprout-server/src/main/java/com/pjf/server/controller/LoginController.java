package com.pjf.server.controller;

import com.pjf.server.entity.User;
import com.pjf.server.entity.UserLogin;
import com.pjf.server.service.IUserService;
import com.pjf.server.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author pjf
 * @since 2022/2/2 16:18
 * 登录控制
 **/
@Api(tags = "登录控制")
@RestController
public class LoginController {
    @Resource
    private IUserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ApiResult login(@RequestBody UserLogin userLogin, HttpServletRequest request) {
        return userService.login(userLogin.getUsername(), userLogin.getPassword(), userLogin.getCode(), request);
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public ApiResult register(@RequestBody User user,String code) {
        return userService.register(user,code);
    }
}
