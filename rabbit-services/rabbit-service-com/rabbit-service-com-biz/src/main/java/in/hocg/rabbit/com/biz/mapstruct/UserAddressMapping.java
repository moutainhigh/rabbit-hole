package in.hocg.rabbit.com.biz.mapstruct;

import in.hocg.rabbit.com.api.pojo.vo.UserAddressFeignVo;
import in.hocg.rabbit.com.biz.entity.UserAddress;
import in.hocg.rabbit.com.biz.pojo.ro.UserAddressClientSaveRo;
import in.hocg.rabbit.com.biz.pojo.vo.UserAddressClientComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.UserAddressClientOrdinaryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2022/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface UserAddressMapping {
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleter", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    UserAddress asUserAddress(UserAddressClientSaveRo ro);

    UserAddressClientOrdinaryVo asUserAddressClientOrdinaryVo(UserAddress entity);

    UserAddressClientComplexVo asUserAddressClientComplexVo(UserAddress entity);

    UserAddressFeignVo asUserAddressFeignVo(UserAddress entity);
}
