package in.hocg.rabbit.chaos.biz.apiimpl;

import in.hocg.boot.named.autoconfiguration.core.AbsNamedServiceExpand;
import in.hocg.rabbit.chaos.api.named.ChaosNamedServiceApi;
import in.hocg.rabbit.com.api.DataDictServiceApi;
import in.hocg.rabbit.com.api.DistrictServiceApi;
import in.hocg.rabbit.com.api.ProjectServiceApi;
import in.hocg.rabbit.com.api.pojo.vo.DataDictItemVo;
import in.hocg.rabbit.com.api.pojo.vo.DistrictComplexVo;
import in.hocg.rabbit.com.api.pojo.vo.ProjectComplexVo;
import in.hocg.rabbit.common.constant.DistrictLevelConstant;
import in.hocg.rabbit.ums.api.UserServiceApi;
import in.hocg.rabbit.ums.api.pojo.vo.AccountVo;
import in.hocg.boot.named.ifc.NamedArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2020/11/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class ChaosNamedApiImpl extends AbsNamedServiceExpand
    implements ChaosNamedServiceApi {
    private final DataDictServiceApi dataDictServiceApi;
    private final UserServiceApi userServiceApi;
    private final DistrictServiceApi districtServiceApi;
    private final ProjectServiceApi projectServiceApi;

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
    public Map<String, Object> loadByAvatarUrl(NamedArgs args) {
        List<Long> values = getValues(args.getValues(), Long.class);
        final List<AccountVo> result = userServiceApi.listAccountVoById(values);
        return this.toMap(result, AccountVo::getId, AccountVo::getAvatarUrl);
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
}
