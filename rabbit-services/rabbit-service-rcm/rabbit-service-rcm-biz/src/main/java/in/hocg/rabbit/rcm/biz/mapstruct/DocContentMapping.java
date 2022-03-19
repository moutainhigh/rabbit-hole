package in.hocg.rabbit.rcm.biz.mapstruct;

import in.hocg.rabbit.rcm.biz.entity.DocContent;
import in.hocg.rabbit.rcm.biz.pojo.ro.PushDocContentRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.HistoryDocContentVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2022/2/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface DocContentMapping {
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "keyword", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dropFlag", ignore = true)
    @Mapping(target = "docId", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    DocContent asDocContent(PushDocContentRo ro);

    @Mapping(target = "title", ignore = true)
    @Mapping(target = "draft", ignore = true)
    HistoryDocContentVo asHistoryDocContentVo(DocContent entity);
}
