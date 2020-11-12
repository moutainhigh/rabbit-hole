package com.github.lotus.chaos.module.wl.service.impl;

import com.github.lotus.chaos.module.wl.entity.Warehouse;
import com.github.lotus.chaos.module.wl.mapper.WarehouseMapper;
import com.github.lotus.chaos.module.wl.service.WarehouseService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [物流模块] 仓库表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WarehouseServiceImpl extends AbstractServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {

}
