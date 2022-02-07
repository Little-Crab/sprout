package com.pjf.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pjf.server.entity.Bill;
import com.pjf.server.entity.TallyBook;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
public interface BillMapper extends BaseMapper<Bill> {
    /**
     * 根据年份，月份查询所有账单
     *
     * @param year  年
     * @param month 月
     * @return 返回账单列表
     */
    List<Bill> getAllBills(String year, String month, TallyBook tallyBook);

    /**
     * 根据账本Id删除账单
     * @param bookId 账本Id
     * @return 返回删除条数
     */
    Integer deleteByBookId(Integer bookId);
}
