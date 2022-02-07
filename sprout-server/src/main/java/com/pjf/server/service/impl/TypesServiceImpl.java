package com.pjf.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pjf.server.entity.Types;
import com.pjf.server.mapper.TypesMapper;
import com.pjf.server.service.ITypesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class TypesServiceImpl extends ServiceImpl<TypesMapper, Types> implements ITypesService {

    @Resource
    private TypesMapper typesMapper;

    /**
     * 根据不同的类型查询不同的类型
     *
     * @param type 类型
     * @return 返回类型列表
     */
    @Override
    public List<Types> getAllTypesByType(Integer type) {
        return typesMapper.getAllTypesByType(type);
    }
}
