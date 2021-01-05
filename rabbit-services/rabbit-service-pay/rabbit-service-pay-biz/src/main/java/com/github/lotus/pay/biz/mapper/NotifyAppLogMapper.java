package com.github.lotus.pay.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.pay.biz.entity.NotifyAppLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [支付网关] 所有通知应用方日志表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-06-06
 */
@Mapper
public interface NotifyAppLogMapper extends BaseMapper<NotifyAppLog> {

}
