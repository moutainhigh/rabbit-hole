package com.github.lotus.pay.biz.mapper;

import com.github.lotus.pay.biz.entity.PaymentPlatform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [支付网关] 第三方支付平台表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Mapper
public interface PaymentPlatformMapper extends BaseMapper<PaymentPlatform> {

}
