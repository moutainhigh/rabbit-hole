package in.hocg.rabbit.com.biz.service.impl;

import in.hocg.rabbit.chaos.api.LbsServiceApi;
import in.hocg.rabbit.chaos.api.pojo.vo.AMapDistrictVo;
import in.hocg.rabbit.com.api.pojo.vo.DistrictComplexVo;
import in.hocg.rabbit.com.biz.entity.District;
import in.hocg.rabbit.com.biz.mapper.DistrictMapper;
import in.hocg.rabbit.com.biz.mapstruct.DistrictMapping;
import in.hocg.rabbit.com.biz.pojo.ro.district.DistrictCompleteRo;
import in.hocg.rabbit.com.biz.pojo.vo.district.DistrictCompleteVo;
import in.hocg.rabbit.com.biz.pojo.vo.district.DistrictTreeVo;
import in.hocg.rabbit.com.biz.service.DistrictService;
import in.hocg.rabbit.com.biz.utils.LBSHelper;
import in.hocg.rabbit.common.constant.DistrictLevelConstant;
import in.hocg.rabbit.com.api.enums.DistrictLevel;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.tree.TreeServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.web.datastruct.tree.Tree;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * [基础模块] 城市规划表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DistrictServiceImpl extends TreeServiceImpl<DistrictMapper, District>
    implements DistrictService {

    private final DistrictMapping mapping;
    private final LbsServiceApi lbsServiceApi;

    @Async
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importByAMapUrl() {
        this.removeAll();
        insertList(null, lbsServiceApi.listDistrict());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DistrictTreeVo> tree() {
        List<District> all = this.getAllDistrict();
        return Tree.getChild(null, all.parallelStream()
            .map(mapping::asDistrictTreeVo)
            .collect(Collectors.toList()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DistrictComplexVo> getChildrenDistrictByAdcode(String adcode) {
        return baseMapper.getChildrenDistrictByAdcode(adcode)
            .stream().map(this::convertComplex)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DistrictComplexVo> listProvince() {
        return LangUtils.toList(selectListByLevel(DistrictLevel.Province), this::convertComplex);
    }

    @Override
    public List<DistrictComplexVo> listProvince(List<String> adcode) {
        return LangUtils.toList(selectListByLevel(DistrictLevel.Province, adcode), this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DistrictComplexVo> listCity() {
        return LangUtils.toList(selectListByLevel(DistrictLevel.City), this::convertComplex);
    }

    @Override
    public List<DistrictComplexVo> listCity(List<String> adcode) {
        return LangUtils.toList(selectListByLevel(DistrictLevel.City, adcode), this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DistrictComplexVo> listCounty() {
        return LangUtils.toList(selectListByLevel(DistrictLevel.Country), this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DistrictComplexVo> listDistrict() {
        return LangUtils.toList(selectListByLevel(DistrictLevel.District), this::convertComplex);
    }

    @Override
    public List<DistrictComplexVo> listDistrict(List<String> adcode) {
        return LangUtils.toList(selectListByLevel(DistrictLevel.District, adcode), this::convertComplex);
    }

    @Override
    public DistrictComplexVo getComplexByCityCode(String cityCode) {
        return this.getCityByCityCode(cityCode).map(this::convertComplex).orElse(null);
    }

    @Override
    public Optional<District> getCityByCityCode(String cityCode) {
        return lambdaQuery().eq(District::getLevel, DistrictLevelConstant.CITY_CODE)
            .eq(District::getCityCode, cityCode).oneOpt();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DistrictCompleteVo> complete(DistrictCompleteRo ro) {
        return baseMapper.complete(ro, ro.ofPage()).getRecords();
    }

    @Override
    public DistrictComplexVo getComplexById(Long id) {
        return this.convertComplex(baseMapper.selectById(id));
    }

    private List<District> selectListByLevel(@NonNull DistrictLevel level) {
        return lambdaQuery().eq(District::getLevel, level.getCode()).list();
    }

    private List<District> selectListByLevel(@NonNull DistrictLevel level, List<String> adcode) {
        return lambdaQuery().eq(District::getLevel, level.getCode()).in(District::getAdcode, adcode).list();
    }

    private DistrictComplexVo convertComplex(District entity) {
        return mapping.asDistrictComplexVo(entity);
    }

    private void insertList(Long parentId, List<AMapDistrictVo> dtos) {
        if (Objects.isNull(dtos)) {
            return;
        }

        AMapDistrictVo dto;
        District entity;
        for (int i = 0; i < dtos.size(); i++) {
            dto = dtos.get(i);
            String adcode = dto.getAdcode();

            DistrictLevel districtLevel = LBSHelper.ofLevel(dto.getLevel());
            BigDecimal lat = LBSHelper.getLat(dto.getCenter());
            BigDecimal lng = LBSHelper.getLng(dto.getCenter());
            String citycode = LBSHelper.getCitycode(dto.getCitycode());

            if (DistrictLevel.Street.equals(districtLevel)) {
                adcode += String.format("%02d", i + 1);
            }
            entity = new District();
            entity.setParentId(parentId);
            entity.setCityCode(citycode);
            entity.setAdcode(adcode);
            entity.setLevel(districtLevel.getCodeStr());
            entity.setTitle(dto.getName());
            entity.setLat(lat);
            entity.setLng(lng);
            validInsertOrUpdate(entity);
            insertList(entity.getId(), dto.getDistricts());
        }
    }

    private void removeAll() {
        lambdaUpdate().remove();
    }

    private List<District> getAllDistrict() {
        return lambdaQuery().list();
    }
}
