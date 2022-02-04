package com.pjf.server.service.impl;

import com.pjf.server.entity.Account;
import com.pjf.server.mapper.AccountMapper;
import com.pjf.server.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pjf.server.utils.ApiResult;
import com.pjf.server.utils.UserUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        account.setMoney(account.getBalance());
        int success = accountMapper.insert(account);
        if (success > 0) {
            return ApiResult.success("账户添加成功");
        }
        return ApiResult.error("账户添加失败");
    }

    /**
     * 根据用户从查询用户账户列表
     *
     * @return 返回账户列表
     */
    @Override
    public List<Account> getAllAccounts() {
        Integer user_id = UserUtils.getCurrentUser().getId();
        return accountMapper.getAllAccounts(user_id);
    }

    /**
     * 更新账户信息
     *
     * @param account 账户
     * @return 返回账户更新结果
     */
    @Override
    public ApiResult updateAccount(Account account) {
        //TODO 考虑是应该直接修改账户余额还是修改初始余额。
        Account originalAccount = accountMapper.selectById(account.getId());
        if (account.getBalance() != null) {
            if (originalAccount.getBalance().compareTo(account.getBalance()) != 0) {
                account.setMoney(originalAccount.getMoney().add(account.getBalance().subtract(originalAccount.getBalance())));
            }
        }
        int result = accountMapper.updateById(account);
        if (result > 0) {
            return ApiResult.success("账户更新成功");
        }
        return ApiResult.error("账户更新失败");
    }
}
