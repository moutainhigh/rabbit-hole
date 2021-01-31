package com.github.lotus.pay.biz.mapper;

import com.github.lotus.pay.biz.entity.PlatformAlipayConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [支付网关] 支付宝配置表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Mapper
public interface PlatformAlipayConfigMapper extends BaseMapper<PlatformAlipayConfig> {

}
