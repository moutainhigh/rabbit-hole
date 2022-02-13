package in.hocg.rabbit.com.biz.apiimpl;

import in.hocg.rabbit.com.api.DocTextServiceApi;
import in.hocg.rabbit.com.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.com.biz.service.DocTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

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
}
