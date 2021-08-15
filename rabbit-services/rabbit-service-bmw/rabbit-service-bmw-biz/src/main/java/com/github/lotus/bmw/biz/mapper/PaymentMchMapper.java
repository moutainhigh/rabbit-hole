package com.github.lotus.bmw.biz.mapper;

import com.github.lotus.bmw.biz.entity.PaymentMch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * <p>
 * [支付模块] 支付商户表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Mapper
public interface PaymentMchMapper extends BaseMapper<PaymentMch> {

    Optional<PaymentMch> getByAccessMchIdAndPayType(@Param("accessMchId") Long accessMchId, @Param("payType") String payType);
}
