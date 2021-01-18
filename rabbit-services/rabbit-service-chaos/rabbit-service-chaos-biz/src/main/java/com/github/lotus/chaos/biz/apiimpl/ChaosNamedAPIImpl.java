package com.github.lotus.chaos.biz.apiimpl;

import cn.hutool.core.convert.Convert;
import com.github.lotus.chaos.api.ChaosNamedAPI;
import com.github.lotus.com.api.DataDictServiceApi;
import com.github.lotus.com.api.DistrictServiceApi;
import com.github.lotus.com.api.pojo.vo.DataDictItemVo;
import com.github.lotus.com.api.pojo.vo.DistrictComplexVo;
import com.github.lotus.common.constant.DistrictLevelConstant;
import com.github.lotus.ums.api.AccountServiceApi;
import com.github.lotus.ums.api.pojo.vo.AccountVo;
import in.hocg.boot.named.autoconfiguration.ifc.NamedArgs;
import in.hocg.boot.utils.LangUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2020/11/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class ChaosNamedAPIImpl implements ChaosNamedAPI {
    private final DataDictServiceApi dataDictServiceApi;
    private final AccountServiceApi accountServiceApi;
    private final DistrictServiceApi districtServiceApi;

    @Override
    public Map<String, Object> loadByDataDict(NamedArgs args) {
        final String type = args.getArgs()[0];
        List<String> values = args.getValues();
        final List<DataDictItemVo> result = dataDictServiceApi.listDataDictItemVoByDictIdAndCode(type, values);
        return this.toMap(result, DataDictItemVo::getCode, DataDictItemVo::getTitle);
    }

    @Override
    public Map<String, Object> loadByUserName(NamedArgs args) {
        List<Long> values = getValues(args.getValues(), Long.class);
        final List<AccountVo> result = accountServiceApi.listAccountVoByAccountId(values);
        return this.toMap(result, AccountVo::getId, AccountVo::getUsername);
    }

    @Override
    public Map<String, Object> loadByNickname(NamedArgs args) {
        List<Long> values = getValues(args.getValues(), Long.class);
        final List<AccountVo> result = accountServiceApi.listAccountVoByAccountId(values);
        return this.toMap(result, AccountVo::getId, AccountVo::getNickname);
    }

    @Override
    public Map<String, Object> loadByDistrictName(NamedArgs args) {
        final String type = args.getArgs()[0];
        List<String> values = args.getValues();
        List<DistrictComplexVo> result = Collections.emptyList();
        switch (type) {
            case DistrictLevelConstant.PROVINCE_CODE: {
                result = districtServiceApi.listProvince(values);
                break;
            }
            case DistrictLevelConstant.CITY_CODE: {
                result = districtServiceApi.listCity(values);
                break;
            }
            case DistrictLevelConstant.DISTRICT_CODE: {
                result = districtServiceApi.listDistrict(values);
                break;
            }
            default:
        }
        return this.toMap(result, DistrictComplexVo::getAdCode, DistrictComplexVo::getTitle);
    }

    private <T> List<T> getValues(List<?> values, Class<T> clazz) {
        return values.parallelStream().map(o -> Convert.convert(clazz, o)).collect(Collectors.toList());
    }

    private <K, V, Z> Map<String, Z> toMap(List<V> values,
                                           Function<? super V, K> keyFunction,
                                           Function<? super V, Z> valueFunction) {
        return LangUtils.toMap(values, v -> String.valueOf(keyFunction.apply(v)), valueFunction);
    }
}
