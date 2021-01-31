package com.github.lotus.pay.biz.mapstruct;

import com.github.lotus.pay.api.pojo.vo.GoPayVo;
import com.github.lotus.pay.biz.support.payment.pojo.response.GoPaymentResponse;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface PaymentPlatformMapping {
    GoPayVo asGoPayVo(GoPaymentResponse result);
}
