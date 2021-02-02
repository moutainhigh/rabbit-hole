package com.github.lotus.pay.biz.mapstruct;

import com.github.lotus.pay.biz.entity.PlatformWxpayConfig;
import com.github.lotus.pay.biz.pojo.ro.AccessPlatformSaveRo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface PlatformWxpayConfigMapping {

    @Mapping(target = "id", ignore = true)
    PlatformWxpayConfig asPlatformWxpayConfig(AccessPlatformSaveRo.WxPayConfig config);
}
