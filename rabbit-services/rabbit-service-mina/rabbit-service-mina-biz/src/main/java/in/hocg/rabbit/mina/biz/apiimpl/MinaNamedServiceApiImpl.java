package in.hocg.rabbit.mina.biz.apiimpl;

import in.hocg.boot.named.autoconfiguration.core.AbsNamedServiceExpand;
import in.hocg.boot.named.ifc.NamedArgs;
import in.hocg.boot.utils.DataDictUtils;
import in.hocg.rabbit.mina.api.MinaServiceName;
import in.hocg.rabbit.mina.api.named.MinaNamedServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class MinaNamedServiceApiImpl extends AbsNamedServiceExpand implements MinaNamedServiceApi {

    @Override
    public Map<String, Object> loadByDataDictName(NamedArgs args) {
        String key = args.getArgs()[0];
        return DataDictUtils.scanMaps(MinaServiceName.PACKAGE).getOrDefault(key, Collections.emptyMap());
    }
}
