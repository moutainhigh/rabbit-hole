package com.github.lotus.chaos.module.support.apiimpl;

import com.github.lotus.chaos.module.com.pojo.vo.district.DistrictComplexVo;
import com.github.lotus.chaos.module.com.service.DataDictService;
import com.github.lotus.chaos.module.com.service.DistrictService;
import com.github.lotus.chaos.module.com.enums.DistrictLevel;
import com.github.lotus.chaos.module.ums.entity.Account;
import com.github.lotus.chaos.module.ums.service.AccountService;
import com.github.lotus.chaos.module.wl.entity.Company;
import com.github.lotus.chaos.module.wl.service.CompanyService;
import com.github.lotus.chaos.modules.support.api.ChaosNamedAPI;
import in.hocg.boot.named.autoconfiguration.ifc.NamedArgs;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.web.datastruct.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by hocgin on 2020/11/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class ChaosNamedAPIImpl implements ChaosNamedAPI {
    private final DataDictService dataDictService;
    private final AccountService accountService;
    private final DistrictService districtService;
    private final CompanyService companyService;

    @Override
    public Map<String, Object> loadByDataDict(NamedArgs args) {
        final String type = args.getArgs()[0];
        final List<KeyValue> result = dataDictService.listDataDictItemDtoByDictIdAndCode(type, args.getValues());
        return this.toMap(result, KeyValue::getValue, KeyValue::getKey);
    }

    @Override
    public Map<String, Object> loadByUserName(NamedArgs args) {
        final List<Account> result = accountService.listAccountByAccountId(args.getValues());
        return this.toMap(result, Account::getId, Account::getUsername);
    }

    @Override
    public Map<String, Object> loadByNickname(NamedArgs args) {
        final List<Account> result = accountService.listAccountByAccountId(args.getValues());
        return this.toMap(result, Account::getId, Account::getNickname);
    }

    @Override
    public Map<String, Object> loadByCompanyName(NamedArgs args) {
        final List<Company> result = companyService.listCompanyByCompanyId(args.getValues());
        return this.toMap(result, Company::getId, Company::getTitle);
    }

    @Override
    public Map<String, Object> loadByDistrictName(NamedArgs args) {
        final String type = args.getArgs()[0];
        List<String> values = args.getValues();
        List<DistrictComplexVo> result = Collections.emptyList();
        switch (type) {
            case DistrictLevel.PROVINCE_CODE: {
                result = districtService.getProvince(values);
                break;
            }
            case DistrictLevel.CITY_CODE: {
                result = districtService.getCity(values);
                break;
            }
            case DistrictLevel.DISTRICT_CODE: {
                result = districtService.getDistrict(values);
                break;
            }
            default:
        }
        return this.toMap(result, DistrictComplexVo::getAdCode, DistrictComplexVo::getTitle);
    }

    private <K, V, Z> Map<String, Z> toMap(List<V> values,
                                           Function<? super V, K> keyFunction,
                                           Function<? super V, Z> valueFunction) {
        return LangUtils.toMap(values, v -> String.valueOf(keyFunction.apply(v)), valueFunction);
    }
}
