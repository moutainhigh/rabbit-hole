package in.hocg.rabbit.bmw.biz.service.impl;

import in.hocg.rabbit.bmw.biz.entity.PaymentMchPayType;
import in.hocg.rabbit.bmw.biz.mapper.PaymentMchPayTypeMapper;
import in.hocg.rabbit.bmw.biz.service.PaymentMchPayTypeService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [支付模块] 支付商户的支付类型表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PaymentMchPayTypeServiceImpl extends AbstractServiceImpl<PaymentMchPayTypeMapper, PaymentMchPayType> implements PaymentMchPayTypeService {

}
