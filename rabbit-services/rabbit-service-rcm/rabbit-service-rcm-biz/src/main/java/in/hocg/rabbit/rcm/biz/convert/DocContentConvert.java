package in.hocg.rabbit.rcm.biz.convert;

import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.rcm.biz.entity.DocContent;
import in.hocg.rabbit.rcm.biz.entity.DocVersion;
import in.hocg.rabbit.rcm.biz.mapstruct.DocContentMapping;
import in.hocg.rabbit.rcm.biz.mapstruct.DocMapping;
import in.hocg.rabbit.rcm.biz.pojo.vo.HistoryDocContentVo;
import in.hocg.rabbit.rcm.biz.service.DocContentService;
import in.hocg.rabbit.rcm.biz.service.DocVersionService;
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
public class DocContentConvert {
    private final DocContentMapping mapping;
    private final DocVersionService docVersionService;

    public HistoryDocContentVo asHistoryDocContentVo(DocContent entity) {
        Optional<DocVersion> docContentOpt = docVersionService.getByDocContentId(entity.getId());
        return mapping.asHistoryDocContentVo(entity)
            .setDraft(LangUtils.callIfNotNull(entity, DocContent::getDropFlag).isPresent())
            .setTitle(docContentOpt.map(DocVersion::getTitle).orElse(null));
    }

}
