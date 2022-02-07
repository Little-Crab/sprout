package com.pjf.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pjf.server.entity.Account;
import com.pjf.server.entity.Bill;
import com.pjf.server.entity.TallyBook;
import com.pjf.server.mapper.AccountMapper;
import com.pjf.server.mapper.BillMapper;
import com.pjf.server.service.IBillService;
import com.pjf.server.utils.ApiResult;
import com.pjf.server.utils.UserUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
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
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

    @Resource
    private BillMapper billMapper;
    @Resource
    private AccountMapper accountMapper;

    /**
     * 根据年份，月份查询所有账单
     *
     * @param year  年
     * @param month 月
     * @return 返回账单列表
     */
    @Override
    public List<Bill> getAllBills(String year, String month, Integer tallyBookId) {
        TallyBook tallyBook = new TallyBook();
        tallyBook.setId(tallyBookId);
        tallyBook.setUserId(UserUtils.getCurrentUser().getId());
        return billMapper.getAllBills(year, month, tallyBook);
    }

    /**
     * 添加账单
     *
     * @param bill 账单信息
     * @return 返回添加结果
     */
    @Override
    public ApiResult addBill(Bill bill) {
        //为账单设置用户ID
        bill.setUserId(UserUtils.getCurrentUser().getId());
        //TODO 后续添加前端用户传输时间，非后端生成
        //设置添加时间
        bill.setCreateDate(LocalDate.now());
        //根据账户Id查询账户信息
        Account account = accountMapper.selectById(bill.getAccountId());
        //如果账单是正数则代表收入，否则代表支出
        account.setMoney(account.getMoney().add(bill.getMoney()));
        int updateAccountResult = accountMapper.updateById(account);
        if (updateAccountResult < 0) {
            return ApiResult.error("账户更新失败");
        }
        int result = billMapper.insert(bill);
        if (result > 0) {
            return ApiResult.success("账单添加成功");

        }
        return ApiResult.error("账单添加失败");
    }

    /**
     * 更新账单，如果钱数改变则更新账户，否则不更新账户
     *
     * @param bill 账单信息
     * @return 返回更新结果
     */
    @Override
    public ApiResult updateBill(Bill bill) {
        Bill originalBill = billMapper.selectById(bill.getId());

        if (bill.getMoney() != null) {
            int i = originalBill.getMoney().compareTo(bill.getMoney());
            if (i != 0) {
                //查询账户，并增加余额或减少余额
                Account account = accountMapper.selectById(originalBill.getAccountId());
                account.setMoney(account.getMoney().add(bill.getMoney().subtract(originalBill.getMoney())));
                int accountResult = accountMapper.updateById(account);
                if (accountResult < 0) {
                    return ApiResult.error("更新账户时失败，请检查或联系管理员");
                }
            }
        }
        int result = billMapper.updateById(bill);
        if (result > 0) {
            return ApiResult.success("账单更新成功");
        }
        return ApiResult.error("账单更新失败");
    }

}
