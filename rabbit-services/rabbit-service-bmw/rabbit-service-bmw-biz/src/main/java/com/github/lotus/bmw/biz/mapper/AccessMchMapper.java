package com.github.lotus.bmw.biz.mapper;

import com.github.lotus.bmw.biz.entity.AccessMch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [支付模块] 接入商户表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Mapper
public interface AccessMchMapper extends BaseMapper<AccessMch> {

    Integer checkSupportPayType(@Param("accessMchId") Long accessMchId, @Param("payType") String payType);
}
