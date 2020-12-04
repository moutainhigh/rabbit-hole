package com.github.lotus.chaos.module.com.service.impl;

import com.github.lotus.chaos.module.com.entity.DataDictItem;
import com.github.lotus.chaos.module.com.mapper.DataDictItemMapper;
import com.github.lotus.chaos.module.com.service.DataDictItemService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [基础模块] 数据字典项表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DataDictItemServiceImpl extends AbstractServiceImpl<DataDictItemMapper, DataDictItem> implements DataDictItemService {

}
