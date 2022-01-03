package in.hocg.rabbit.mall.biz.service.impl;

import in.hocg.rabbit.mall.biz.entity.Shopcart;
import in.hocg.rabbit.mall.biz.mapper.ShopcartMapper;
import in.hocg.rabbit.mall.biz.service.ShopcartService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [订单模块] 购物车表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ShopcartServiceImpl extends AbstractServiceImpl<ShopcartMapper, Shopcart> implements ShopcartService {

}
