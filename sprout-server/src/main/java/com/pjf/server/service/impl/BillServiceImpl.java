package com.pjf.server.service.impl;

import com.pjf.server.entity.Bill;
import com.pjf.server.entity.TallyBook;
import com.pjf.server.mapper.BillMapper;
import com.pjf.server.service.IBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pjf.server.utils.ApiResult;
import com.pjf.server.utils.UserUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    /**
     * 根据年份，月份查询所有账单
     *
     * @param year  年
     * @param mouth 月
     * @return 返回账单列表
     */
    @Override
    public List<Bill> getAllBills(String year, String mouth, Integer tallyBookId) {
        TallyBook tallyBook = new TallyBook();
        tallyBook.setId(tallyBookId);
        tallyBook.setUserId(UserUtils.getCurrentUser().getId());
        return billMapper.getAllBills(year, mouth, tallyBook);
    }

    /**
     * 添加账单
     *
     * @param bill 账单信息
     * @return 返回添加结果
     */
    @Override
    public ApiResult addBill(Bill bill) {
        bill.setUserId(UserUtils.getCurrentUser().getId());
        bill.setCreateDate(LocalDate.now());
        int result = billMapper.insert(bill);
        if (result > 0) {
            return ApiResult.success("账单添加成功");
        }
        return ApiResult.error("账单添加失败");
    }

}
