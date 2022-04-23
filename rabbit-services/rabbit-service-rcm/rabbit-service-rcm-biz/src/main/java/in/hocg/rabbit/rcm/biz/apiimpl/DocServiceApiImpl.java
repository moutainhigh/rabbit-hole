package in.hocg.rabbit.rcm.biz.apiimpl;

import cn.hutool.core.bean.BeanUtil;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.rcm.api.DocServiceApi;
import in.hocg.rabbit.rcm.api.pojo.ro.CreateDocRo;
import in.hocg.rabbit.rcm.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.rcm.api.pojo.vo.DraftDocVo;
import in.hocg.rabbit.rcm.api.pojo.vo.PublishedDocVo;
import in.hocg.rabbit.rcm.biz.service.DocService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2022/2/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = {"rcm::文档", "rcm"})
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class DocServiceApiImpl implements DocServiceApi {
    private final DocService service;

    @Override
    public Long createDoc(CreateDocRo ro) {
        return service.createDoc(ro);
    }

    @Override
    public void publishContent(PublishDocTextRo ro) {
        service.publishContent(ro);
    }

    @Override
    public PublishedDocVo getPublishedDoc(Long id) {
        return BeanUtil.copyProperties(service.getPublishedById(id), PublishedDocVo.class);
    }

    @Override
    public DraftDocVo getDraftedDoc(Long id) {
        return BeanUtil.copyProperties(service.getDraftedById(id), DraftDocVo.class);
    }
}
