package in.hocg.rabbit.rcm.biz.apiimpl;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.boot.named.autoconfiguration.core.AbsNamedServiceExpand;
import in.hocg.boot.named.ifc.NamedArgs;
import in.hocg.boot.utils.DataDictUtils;
import in.hocg.rabbit.rcm.api.RcmServiceName;
import in.hocg.rabbit.rcm.api.named.RcmNamedServiceApi;
import in.hocg.rabbit.rcm.biz.entity.PostCategory;
import in.hocg.rabbit.rcm.biz.service.PostCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2022/2/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class RcmNamedServiceApiImpl extends AbsNamedServiceExpand
    implements RcmNamedServiceApi {
    private final PostCategoryService postCategoryService;

    @Override
    public Map<String, Object> loadByDataDict(NamedArgs args) {
        String key = args.getArgs()[0];
        return DataDictUtils.scanMaps(RcmServiceName.PACKAGE).getOrDefault(key, Collections.emptyMap());
    }

    @Override
    public Map<String, Object> loadByCategoryName(NamedArgs args) {
        return this.toMap(postCategoryService.listByIds(args.getValues()), CommonEntity::getId, PostCategory::getTitle);
    }
}
