package com.github.lotus.pay.biz.mapstruct;

import com.github.lotus.pay.biz.entity.AccessPlatform;
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
public interface AccessPlatformMapping {
    @Mapping(target = "createdIp", ignore = true)
    @Mapping(target = "refType", ignore = true)
    @Mapping(target = "refId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    AccessPlatform asAccessPlatform(AccessPlatformSaveRo ro);
}
