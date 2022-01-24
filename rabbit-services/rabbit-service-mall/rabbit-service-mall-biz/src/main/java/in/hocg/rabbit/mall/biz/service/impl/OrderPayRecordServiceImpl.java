package in.hocg.rabbit.mall.biz.service.impl;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import in.hocg.rabbit.mall.biz.entity.OrderPayRecord;
import in.hocg.rabbit.mall.biz.mapper.OrderPayRecordMapper;
import in.hocg.rabbit.mall.biz.service.OrderPayRecordService;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [订单模块] 订单支付记录表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OrderPayRecordServiceImpl extends AbstractServiceImpl<OrderPayRecordMapper, OrderPayRecord> implements OrderPayRecordService {

}
