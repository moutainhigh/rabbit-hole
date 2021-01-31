package com.github.lotus.pay.biz.mapstruct;

import com.github.lotus.pay.biz.entity.PlatformAlipayConfig;
import com.github.lotus.pay.biz.pojo.ro.AccessPlatformInsertRo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface PlatformAlipayConfigMapping {

    @Mapping(target = "id", ignore = true)
    PlatformAlipayConfig asPlatformAlipayConfig(AccessPlatformInsertRo.AliPayConfig config);
}
