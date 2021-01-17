package com.github.lotus.pay.biz.mapstruct;

import com.github.lotus.pay.api.pojo.vo.PaymentWayVo;
import com.github.lotus.pay.biz.entity.PaymentWayRuleItem;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by hocgin on 2020/7/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface PaymentWayRuleItemMapping {

    List<PaymentWayVo> asPaymentWayVo(List<PaymentWayRuleItem> result);

    PaymentWayVo asPaymentWayVo(PaymentWayRuleItem result);


}
