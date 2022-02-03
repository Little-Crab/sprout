package com.pjf.server.service.impl;

import com.pjf.server.entity.Account;
import com.pjf.server.mapper.AccountMapper;
import com.pjf.server.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pjf.server.utils.ApiResult;
import com.pjf.server.utils.UserUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Resource
    private AccountMapper accountMapper;

    /**
     * 添加账户
     *
     * @param account 账户信息
     * @return 返回添加结果
     */
    @Override
    public ApiResult addAccount(Account account) {
        account.setUserId(UserUtils.getCurrentUser().getId());
        int success = accountMapper.insert(account);
        if (success > 0) {
            return ApiResult.success("账户添加成功");
        }
        return ApiResult.error("账户添加失败");
    }
}
