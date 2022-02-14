package in.hocg.rabbit.com.biz.apiimpl;

import in.hocg.rabbit.com.api.DocTextServiceApi;
import in.hocg.rabbit.com.api.pojo.ro.BatchPublishDocTextRo;
import in.hocg.rabbit.com.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.com.api.pojo.vo.DocTextVo;
import in.hocg.rabbit.com.biz.service.DocTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by hocgin on 2022/2/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DocTextServiceApiImpl implements DocTextServiceApi {
    private final DocTextService docTextService;

    @Override
    public Long publish(PublishDocTextRo ro) {
        return docTextService.publish(ro);
    }

    @Override
    public DocTextVo getDocTextById(Long id) {
        return docTextService.getDocTextById(id);
    }

    @Override
    public void batchPublish(BatchPublishDocTextRo ro) {
        docTextService.batchPublish(ro);
    }

    @Override
    public List<DocTextVo> listByRefTypeAndRefId(@NotNull String refType, @NotNull Long refId) {
        return docTextService.listByRefTypeAndRefId(refType, refId);
    }
}
