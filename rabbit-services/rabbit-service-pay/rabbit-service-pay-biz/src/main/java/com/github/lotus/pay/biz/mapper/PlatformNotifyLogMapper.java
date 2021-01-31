package com.github.lotus.pay.biz.mapper;

import com.github.lotus.pay.biz.entity.PlatformNotifyLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [支付网关] 所有第三方支付通知日志表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-31
 */
@Mapper
public interface PlatformNotifyLogMapper extends BaseMapper<PlatformNotifyLog> {

}
