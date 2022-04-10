package in.hocg.rabbit.owp.biz.mapstruct;

import in.hocg.rabbit.owp.api.pojo.vo.DevAppVo;
import in.hocg.rabbit.owp.biz.entity.DeveloperApp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */

@Mapper(componentModel = "spring")
public interface DeveloperAppMapping {
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "userId", source = "developerUserId")
    @Mapping(target = "expired", source = "enabled")
    DevAppVo asDevAppVo(DeveloperApp entity);
}
