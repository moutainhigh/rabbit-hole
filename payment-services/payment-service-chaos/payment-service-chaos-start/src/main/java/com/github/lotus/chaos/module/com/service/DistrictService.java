package com.github.lotus.chaos.module.com.service;

import com.github.lotus.chaos.module.com.entity.District;
import com.github.lotus.chaos.module.com.pojo.vo.district.DistrictComplexVo;
import com.github.lotus.chaos.module.com.pojo.vo.district.DistrictTreeVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

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

    List<DistrictComplexVo> getChildrenDistrictByAdcode(String adCode);

    List<DistrictComplexVo> getProvince();

    List<DistrictComplexVo> getCity();

    List<DistrictComplexVo> getCity(List<String> adcode);

    List<DistrictComplexVo> getCounty();

    List<DistrictComplexVo> getDistrict();

    List<DistrictComplexVo> getProvince(List<String> adcode);

    List<DistrictComplexVo> getDistrict(List<String> adcode);
}
