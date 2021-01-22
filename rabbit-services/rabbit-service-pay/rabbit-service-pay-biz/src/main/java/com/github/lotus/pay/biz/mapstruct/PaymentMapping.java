package com.github.lotus.pay.biz.mapstruct;

import com.github.lotus.pay.api.pojo.vo.GoPayVo;
import com.github.lotus.pay.biz.support.payment.pojo.response.GoPaymentResponse;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2020/6/7.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface PaymentMapping {

    GoPayVo asGoPayVo(GoPaymentResponse result);
}
