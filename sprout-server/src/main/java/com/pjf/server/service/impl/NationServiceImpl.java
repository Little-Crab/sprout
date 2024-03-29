package com.pjf.server.service.impl;

import com.pjf.server.entity.Nation;
import com.pjf.server.mapper.NationMapper;
import com.pjf.server.service.INationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 18:16:55
 */
@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

}
