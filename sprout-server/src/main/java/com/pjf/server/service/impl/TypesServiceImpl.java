package com.pjf.server.service.impl;

import com.pjf.server.entity.Types;
import com.pjf.server.mapper.TypesMapper;
import com.pjf.server.service.ITypesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
@Service
public class TypesServiceImpl extends ServiceImpl<TypesMapper, Types> implements ITypesService {

}
