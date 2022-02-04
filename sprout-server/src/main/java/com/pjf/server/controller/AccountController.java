package com.pjf.server.controller;


import com.pjf.server.entity.Account;
import com.pjf.server.service.IAccountService;
import com.pjf.server.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
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

    @ApiOperation("删除账户")
    @DeleteMapping("/{id}")
    public ApiResult deleteAccount(@PathVariable Integer id) {
        if (accountService.removeById(id)) {
            return ApiResult.success("账户删除成功");
        }
        return ApiResult.error("账户删除失败");
    }

    @Transactional
    @ApiOperation("更新账户")
    @PutMapping("/")
    public ApiResult updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @ApiOperation("获取全部账户")
    @GetMapping("/")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
