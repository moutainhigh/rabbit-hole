package in.hocg.rabbit.rcm.biz.convert;

import in.hocg.rabbit.common.utils.DbUtils;
import in.hocg.rabbit.rcm.biz.entity.Doc;
import in.hocg.rabbit.rcm.biz.entity.DocContent;
import in.hocg.rabbit.rcm.biz.mapstruct.DocMapping;
import in.hocg.rabbit.rcm.biz.pojo.vo.DraftDocVo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostSummaryVo;
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

    public PostSummaryVo asPostSummaryVo(Doc entity) {
        return mapping.asPostSummaryVo(entity);
    }

    public PublishedDocVo asPublishedDocVo(Doc entity) {
        PublishedDocVo result = mapping.asPublishedDocVo(entity);
        Optional<DocContent> contentOpt = docContentService.getPublishedByDocId(entity.getId());
        if (contentOpt.isPresent()) {
            DocContent docContent = contentOpt.get();
            result.setKeyword(DbUtils.toList(docContent.getKeyword()));
            result.setSummary(docContent.getSummary());
            result.setContent(docContent.getContent());
        }
        return result;
    }

    public DraftDocVo asDraftDocVo(Doc entity) {
        Optional<DocContent> content = docContentService.getDraftedByDocId(entity.getId());
        return mapping.asDraftDocVo(entity)
            .setKeyword(content.map(DocContent::getKeyword).map(DbUtils::toList).orElse(null))
            .setContentId(content.map(DocContent::getId).orElse(null))
            .setDraft(content.map(DocContent::getDropFlag).isPresent())
            .setSummary(content.map(DocContent::getSummary).orElse(null))
            .setContent(content.map(DocContent::getContent).orElse(null));
    }
}
