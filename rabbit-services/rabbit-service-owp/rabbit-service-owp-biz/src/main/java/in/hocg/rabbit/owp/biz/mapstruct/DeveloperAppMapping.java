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
    default DevAppVo asDevAppVo(DeveloperApp entity) {
        return new DevAppVo()
            .setUserId(entity.getDeveloperUserId())
            .setExpired(!entity.getEnabled());
    }
}
