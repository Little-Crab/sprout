package com.pjf.server.service;

import com.pjf.server.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pjf.server.utils.ApiResult;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
public interface IAccountService extends IService<Account> {

    /**
     * 添加账户
     *
     * @param account 账户信息
     * @return 返回添加结果
     */
    ApiResult addAccount(Account account);

    /**
     * 根据用户从查询用户账户列表
     *
     * @return 返回账户列表
     */
    List<Account> getAllAccounts();

    /**
     * 更新账户信息
     *
     * @param account 账户
     * @return 返回账户更新结果
     */
    ApiResult updateAccount(Account account);
}
