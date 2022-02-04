package com.pjf.server.mapper;

import com.pjf.server.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 根据用户从查询用户账户列表
     * @return 返回账户列表
     */
    List<Account> getAllAccounts(Integer user_id);
}
