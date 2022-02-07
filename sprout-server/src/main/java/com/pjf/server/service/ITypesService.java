package com.pjf.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pjf.server.entity.Types;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
public interface ITypesService extends IService<Types> {

    /**
     * 根据不同的类型查询不同的类型
     *
     * @param type 类型
     * @return 返回类型列表
     */
    List<Types> getAllTypesByType(Integer type);
}
