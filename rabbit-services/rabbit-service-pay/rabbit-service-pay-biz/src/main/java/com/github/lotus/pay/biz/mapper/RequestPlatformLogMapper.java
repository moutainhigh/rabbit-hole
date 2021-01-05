package com.github.lotus.pay.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.pay.biz.entity.RequestPlatformLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [支付网关] 所有和第三方支付交易日志表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
@Mapper
public interface RequestPlatformLogMapper extends BaseMapper<RequestPlatformLog> {

}
