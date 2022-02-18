package com.pjf.server.controller;

import com.pjf.server.entity.User;
import com.pjf.server.entity.UserLogin;
import com.pjf.server.service.ILoginService;
import com.pjf.server.utils.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author pjf
 * @since 2022/2/2 16:18
 * 登录控制
 **/
@Tag(name = "登录控制")
@CrossOrigin
@RestController
@RequestMapping("/sprout")
public class LoginController {
    @Resource
    private ILoginService loginService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public ApiResult login(@RequestBody UserLogin userLogin, HttpServletRequest request) {
        return loginService.login(userLogin.getUsername(), userLogin.getPassword(), userLogin.getCode(), request);
    }

    @Operation(summary = "手机号登录")
    @PostMapping("/login/phone")
    public ApiResult loginPhone(@RequestBody UserLogin userLogin, HttpServletRequest request) {
        return loginService.loginPhone(userLogin.getPhone(), userLogin.getCode(), request);
    }

    @Operation(summary = "注册")
    @PostMapping("/register")
    public ApiResult register(@RequestBody User user, String code) {
        return loginService.register(user, code);
    }

}
