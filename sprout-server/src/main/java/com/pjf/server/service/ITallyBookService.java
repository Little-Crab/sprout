package com.pjf.server.service;

import com.pjf.server.entity.TallyBook;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pjf.server.utils.ApiResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
public interface ITallyBookService extends IService<TallyBook> {

    /**
     * 添加一个账单
     * @param tallyBook 账单
     * @return 返回添加结果
     */
    ApiResult addTallyBook(TallyBook tallyBook);
}
