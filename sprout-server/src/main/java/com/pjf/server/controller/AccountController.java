package com.pjf.server.controller;


import com.pjf.server.entity.Account;
import com.pjf.server.entity.Bill;
import com.pjf.server.service.IAccountService;
import com.pjf.server.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
@RestController
@Api(tags = "账户管理")
@RequestMapping("/account")
public class AccountController {
    @Resource
    private IAccountService accountService;

    @ApiOperation("添加账户")
    @PostMapping("/")
    public ApiResult addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }
}
