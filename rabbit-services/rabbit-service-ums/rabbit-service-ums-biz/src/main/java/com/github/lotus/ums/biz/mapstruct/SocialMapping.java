package com.github.lotus.ums.biz.mapstruct;

import com.github.lotus.ums.api.pojo.ro.InsertSocialRo;
import com.github.lotus.ums.biz.entity.Social;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface SocialMapping {
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Social asSocial(InsertSocialRo ro);
}
