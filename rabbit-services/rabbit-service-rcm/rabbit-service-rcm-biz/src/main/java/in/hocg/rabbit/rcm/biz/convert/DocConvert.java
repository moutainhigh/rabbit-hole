package in.hocg.rabbit.rcm.biz.convert;

import in.hocg.rabbit.rcm.biz.entity.Doc;
import in.hocg.rabbit.rcm.biz.entity.DocContent;
import in.hocg.rabbit.rcm.biz.mapstruct.DocMapping;
import in.hocg.rabbit.rcm.biz.pojo.vo.DraftDocVo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PublishedDocVo;
import in.hocg.rabbit.rcm.biz.service.DocContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by hocgin on 2022/2/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DocConvert {
    private final DocContentService docContentService;
    private final DocMapping mapping;

    public PublishedDocVo asPublishedDocVo(Doc entity) {
        Optional<DocContent> content = docContentService.getPublishedByDocId(entity.getId());
        return mapping.asPublishedDocVo(entity)
            .setDescription(content.map(DocContent::getDescription).orElse(null))
            .setContent(content.map(DocContent::getContent).orElse(null));
    }

    public DraftDocVo asDraftDocVo(Doc entity) {
        Optional<DocContent> content = docContentService.getDraftedByDocId(entity.getId());
        return mapping.asDraftDocVo(entity)
            .setContentId(content.map(DocContent::getId).orElse(null))
            .setDraft(content.map(DocContent::getDropFlag).isPresent())
            .setDescription(content.map(DocContent::getDescription).orElse(null))
            .setContent(content.map(DocContent::getContent).orElse(null));
    }
}
