package com.github.lotus.wl.biz.basic;

import com.github.lotus.wl.biz.entity.Company;
import com.github.lotus.wl.biz.service.CompanyService;
import in.hocg.boot.named.autoconfiguration.annotation.NamedService;
import in.hocg.boot.named.autoconfiguration.ifc.NamedArgs;
import in.hocg.boot.named.autoconfiguration.ifc.NamedHandler;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by hocgin on 2020/12/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class WlNamedService implements NamedService {
    public final static String WL_CompanyName = "WL_CompanyName";
    private final CompanyService companyService;

    @NamedHandler(WlNamedService.WL_CompanyName)
    public Map<String, Object> loadByCompanyName(NamedArgs args) {
        final List<Company> result = companyService.listCompanyByCompanyId(args.getValues());
        return this.toMap(result, Company::getId, Company::getTitle);
    }

    private <K, V, Z> Map<String, Z> toMap(List<V> values,
                                           Function<? super V, K> keyFunction,
                                           Function<? super V, Z> valueFunction) {
        return LangUtils.toMap(values, v -> String.valueOf(keyFunction.apply(v)), valueFunction);
    }
}
