package com.github.lotus.chaos.biz.apiimpl;

import cn.hutool.core.convert.Convert;
import com.github.lotus.chaos.api.ChaosNamedApi;
import com.github.lotus.com.api.DataDictServiceApi;
import com.github.lotus.com.api.DistrictServiceApi;
import com.github.lotus.com.api.ProjectServiceApi;
import com.github.lotus.com.api.pojo.vo.DataDictItemVo;
import com.github.lotus.com.api.pojo.vo.DistrictComplexVo;
import com.github.lotus.com.api.pojo.vo.ProjectComplexVo;
import com.github.lotus.common.constant.DistrictLevelConstant;
import com.github.lotus.pay.api.AccessAppServiceApi;
import com.github.lotus.pay.api.pojo.vo.AccessAppOrdinaryVo;
import com.github.lotus.ums.api.UserServiceApi;
import com.github.lotus.ums.api.pojo.vo.AccountVo;
import in.hocg.boot.named.ifc.NamedArgs;
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
public class ChaosNamedApiImpl implements ChaosNamedApi {
    private final DataDictServiceApi dataDictServiceApi;
    private final UserServiceApi userServiceApi;
    private final DistrictServiceApi districtServiceApi;
    private final ProjectServiceApi projectServiceApi;
    private final AccessAppServiceApi accessAppServiceApi;

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
        final List<AccountVo> result = userServiceApi.listAccountVoById(values);
        return this.toMap(result, AccountVo::getId, AccountVo::getUsername);
    }

    @Override
    public Map<String, Object> loadByNickname(NamedArgs args) {
        List<Long> values = getValues(args.getValues(), Long.class);
        final List<AccountVo> result = userServiceApi.listAccountVoById(values);
        return this.toMap(result, AccountVo::getId, AccountVo::getNickname);
    }

    @Override
    public Map<String, Object> loadByProjectName(NamedArgs args) {
        List<Long> values = getValues(args.getValues(), Long.class);
        final List<ProjectComplexVo> result = projectServiceApi.listComplexById(values);
        return this.toMap(result, ProjectComplexVo::getId, ProjectComplexVo::getTitle);
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
        return this.toMap(result, DistrictComplexVo::getAdcode, DistrictComplexVo::getTitle);
    }

    @Override
    public Map<String, Object> loadByAccessAppName(NamedArgs args) {
        List<Long> values = getValues(args.getValues(), Long.class);
        List<AccessAppOrdinaryVo> result = accessAppServiceApi.listOrdinaryById(values);
        return this.toMap(result, AccessAppOrdinaryVo::getId, AccessAppOrdinaryVo::getTitle);
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
