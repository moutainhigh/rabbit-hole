package in.hocg.rabbit.rcm.biz.mapstruct;

import com.alibaba.nacos.api.cmdb.pojo.Label;
import in.hocg.rabbit.rcm.api.pojo.ro.CreateDocRo;
import in.hocg.rabbit.rcm.biz.entity.Doc;
import in.hocg.rabbit.rcm.biz.pojo.vo.DraftDocVo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PublishedDocVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2022/2/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface DocMapping {

    @Mapping(target = "content", ignore = true)
    @Mapping(target = "ownerUserName", ignore = true)
    @Mapping(target = "description", ignore = true)
    PublishedDocVo asPublishedDocVo(Doc doc);

    @Mapping(target = "draft", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "contentId", ignore = true)
    @Mapping(target = "ownerUserName", ignore = true)
    @Mapping(target = "content", ignore = true)
    DraftDocVo asDraftDocVo(Doc entity);

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Doc asDoc(CreateDocRo ro);
}
