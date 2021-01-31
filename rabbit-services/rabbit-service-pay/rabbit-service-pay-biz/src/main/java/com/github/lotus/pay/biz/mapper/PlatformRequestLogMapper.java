package com.github.lotus.pay.biz.mapper;

import com.github.lotus.pay.biz.entity.PlatformRequestLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [支付网关] 所有和第三方支付交易日志表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Mapper
public interface PlatformRequestLogMapper extends BaseMapper<PlatformRequestLog> {

}
