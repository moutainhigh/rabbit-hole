package com.github.lotus.bmw.biz.mapstruct;

import com.github.lotus.bmw.biz.entity.Account;
import com.github.lotus.bmw.biz.pojo.dto.CreateAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface AccountMapping {
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "useScenes", ignore = true)
    @Mapping(target = "paymentMchId", ignore = true)
    @Mapping(target = "mchAccount", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "accessMchId", ignore = true)
    Account asAccount(CreateAccountDto dto);
}
