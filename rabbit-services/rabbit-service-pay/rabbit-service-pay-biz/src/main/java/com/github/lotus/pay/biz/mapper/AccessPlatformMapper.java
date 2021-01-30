package com.github.lotus.pay.biz.mapper;

import com.github.lotus.pay.biz.entity.AccessPlatform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * <p>
 * [支付网关] 接入平台表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Mapper
public interface AccessPlatformMapper extends BaseMapper<AccessPlatform> {

    Optional<AccessPlatform> getByAccessAppIdAndPayMode(@Param("accessAppId") Long accessAppId, @Param("payMode") String payMode);
}
