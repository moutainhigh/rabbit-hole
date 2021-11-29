package in.hocg.rabbit.ums.biz.mapstruct;

import in.hocg.rabbit.ums.api.pojo.ro.InsertSocialRo;
import in.hocg.rabbit.ums.biz.entity.Social;
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
