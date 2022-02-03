package com.pjf.server.service.impl;

import com.pjf.server.entity.TallyBook;
import com.pjf.server.mapper.TallyBookMapper;
import com.pjf.server.service.ITallyBookService;
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
public class TallyBookServiceImpl extends ServiceImpl<TallyBookMapper, TallyBook> implements ITallyBookService {
    @Resource
    private TallyBookMapper bookMapper;

    /**
     * 添加一个账单
     *
     * @param tallyBook 账单
     * @return 返回添加结果
     */
    @Override
    public ApiResult addTallyBook(TallyBook tallyBook) {
        tallyBook.setUserId(UserUtils.getCurrentUser().getId());
        int success = bookMapper.insert(tallyBook);
        if (success > 0) {
            return ApiResult.success("添加成功");
        }
        return ApiResult.success("添加失败");
    }
}
