package in.hocg.rabbit.com.biz.service;

import in.hocg.rabbit.com.biz.entity.District;
import in.hocg.rabbit.com.biz.pojo.ro.district.DistrictCompleteRo;
import in.hocg.rabbit.com.biz.pojo.vo.district.DistrictCompleteVo;
import in.hocg.rabbit.com.api.pojo.vo.DistrictComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.district.DistrictTreeVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * [基础模块] 城市规划表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
public interface DistrictService extends AbstractService<District> {

    void importByAMapUrl();

    List<DistrictTreeVo> tree();

    List<DistrictComplexVo> getChildrenDistrictByAdcode(String adcode);

    List<DistrictComplexVo> listProvince();

    List<DistrictComplexVo> listCity();

    List<DistrictComplexVo> listCity(List<String> adcode);

    List<DistrictComplexVo> listCounty();

    List<DistrictComplexVo> listDistrict();

    List<DistrictComplexVo> listProvince(List<String> adcode);

    List<DistrictComplexVo> listDistrict(List<String> adcode);

    DistrictComplexVo getComplexByCityCode(String cityCode);

    Optional<District> getCityByCityCode(String cityCode);

    List<DistrictCompleteVo> complete(DistrictCompleteRo ro);

    DistrictComplexVo getComplexById(Long parentId);
}
