package com.pjf.server.service;

import com.pjf.server.entity.Bill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pjf.server.entity.TallyBook;
import com.pjf.server.utils.ApiResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
public interface IBillService extends IService<Bill> {

    /**
     * 根据年份，月份查询所有账单
     * @param year 年
     * @param mouth 月
     * @return 返回账单列表
     */
    List<Bill> getAllBills(String year, String mouth, Integer tallyBookId);

    /**
     * 添加账单
     * @param bill 账单信息
     * @return 返回添加结果
     */
    ApiResult addBill(Bill bill);
}
