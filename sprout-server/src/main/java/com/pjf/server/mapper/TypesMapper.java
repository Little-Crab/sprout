package com.pjf.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pjf.server.entity.Types;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
public interface TypesMapper extends BaseMapper<Types> {

    /**
     * 根据不同的类型查询不同的类型
     *
     * @param type 类型
     * @return 返回类型列表
     */
    List<Types> getAllTypesByType(Integer type);
}
