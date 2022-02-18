package com.pjf.server.controller;


import com.pjf.server.entity.Account;
import com.pjf.server.service.IAccountService;
import com.pjf.server.utils.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "账户管理")
@RequestMapping("/sprout/keep/account")
@SecurityRequirement(name = "Authorization")
public class AccountController {
    @Resource
    private IAccountService accountService;

    @Operation(summary = "添加账户")
    @PostMapping("/")
    public ApiResult addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @Operation(summary = "删除账户")
    @DeleteMapping("/{id}")
    public ApiResult deleteAccount(@PathVariable Integer id) {
        if (accountService.removeById(id)) {
            return ApiResult.success("账户删除成功");
        }
        return ApiResult.error("账户删除失败");
    }

    @Transactional
    @Operation(summary = "更新账户")
    @PutMapping("/")
    public ApiResult updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @Operation(summary = "获取全部账户")
    @GetMapping("/")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
