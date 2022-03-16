package in.hocg.rabbit.mina.biz.mapstruct;

import in.hocg.rabbit.mina.biz.entity.ShareContent;
import in.hocg.rabbit.mina.biz.pojo.ro.ShareContentSaveRo;
import in.hocg.rabbit.mina.biz.pojo.vo.ShareContentVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2022/3/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface ShareContentMapping {
    ShareContentVo asShareContentVo(ShareContent entity);

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "encoding", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ShareContent asShareContent(ShareContentSaveRo ro);
}
