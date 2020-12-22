package com.github.lotus.chaos.biz.modules.support.apiimpl;

import com.github.lotus.chaos.api.modules.com.constant.DistrictLevel;
import com.github.lotus.chaos.api.modules.support.ChaosNamedAPI;
import com.github.lotus.chaos.biz.modules.com.pojo.vo.district.DistrictComplexVo;
import com.github.lotus.chaos.biz.modules.com.service.DataDictService;
import com.github.lotus.chaos.biz.modules.com.service.DistrictService;
import com.github.lotus.ums.api.AccountServiceApi;
import com.github.lotus.ums.api.pojo.vo.AccountVo;
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
    private final AccountServiceApi accountService;
    private final DistrictService districtService;

    @Override
    public Map<String, Object> loadByDataDict(NamedArgs args) {
        final String type = args.getArgs()[0];
        final List<KeyValue> result = dataDictService.listDataDictItemDtoByDictIdAndCode(type, args.getValues());
        return this.toMap(result, KeyValue::getValue, KeyValue::getKey);
    }

    @Override
    public Map<String, Object> loadByUserName(NamedArgs args) {
        final List<AccountVo> result = accountService.listAccountVoByAccountId(args.getValues());
        return this.toMap(result, AccountVo::getId, AccountVo::getUsername);
    }

    @Override
    public Map<String, Object> loadByNickname(NamedArgs args) {
        final List<AccountVo> result = accountService.listAccountVoByAccountId(args.getValues());
        return this.toMap(result, AccountVo::getId, AccountVo::getNickname);
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
