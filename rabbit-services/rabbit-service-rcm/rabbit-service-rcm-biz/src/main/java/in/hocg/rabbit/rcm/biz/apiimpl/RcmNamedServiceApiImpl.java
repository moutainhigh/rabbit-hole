package in.hocg.rabbit.rcm.biz.apiimpl;

import in.hocg.boot.named.ifc.NamedArgs;
import in.hocg.boot.utils.DataDictUtils;
import in.hocg.rabbit.rcm.api.RcmServiceName;
import in.hocg.rabbit.rcm.api.named.RcmNamedServiceApi;
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
public class RcmNamedServiceApiImpl implements RcmNamedServiceApi {
    @Override
    public Map<String, Object> loadByDataDict(NamedArgs args) {
        String key = args.getArgs()[0];
        return DataDictUtils.scanMaps(RcmServiceName.PACKAGE).getOrDefault(key, Collections.emptyMap());
    }
}
