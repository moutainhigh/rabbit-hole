package in.hocg.rabbit.com.biz.apiimpl;

import in.hocg.rabbit.com.api.ComServiceName;
import in.hocg.rabbit.com.api.DocTextServiceApi;
import in.hocg.rabbit.com.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.com.api.pojo.vo.DocTextVo;
import in.hocg.rabbit.com.biz.service.DocTextService;
import io.swagger.annotations.Api;
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
    public void publish(PublishDocTextRo ro) {
        docTextService.publish(ro);
    }

    @Override
    public List<DocTextVo> listByRefTypeAndRefId(@NotNull String refType, @NotNull Long refId) {
        return docTextService.listByRefTypeAndRefId(refType, refId);
    }
}
