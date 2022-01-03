package in.hocg.rabbit.mall.biz.apiimpl;

import in.hocg.boot.named.autoconfiguration.core.AbsNamedServiceExpand;
import in.hocg.boot.named.ifc.NamedArgs;
import in.hocg.boot.utils.DataDictUtils;
import in.hocg.rabbit.bmw.api.BmwServiceName;
import in.hocg.rabbit.mall.api.MallServiceName;
import in.hocg.rabbit.mall.api.named.MallNamedServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.interceptor.ExposeBeanNameAdvisors;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Created by hocgin on 2022/1/2
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class MallNamedServiceApiImpl extends AbsNamedServiceExpand implements MallNamedServiceApi {

    @Override
    public Map<String, Object> loadByDataDictName(NamedArgs args) {
        String key = args.getArgs()[0];
        return DataDictUtils.scanMaps(MallServiceName.PACKAGE).getOrDefault(key, Collections.emptyMap());
    }
}
