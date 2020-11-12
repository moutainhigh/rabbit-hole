package com.github.lotus.chaos.module.com.service.impl;

import com.github.lotus.chaos.module.com.entity.District;
import com.github.lotus.chaos.module.com.mapper.DistrictMapper;
import com.github.lotus.chaos.module.com.mapstruct.DistrictMapping;
import com.github.lotus.chaos.module.com.pojo.vo.district.DistrictComplexVo;
import com.github.lotus.chaos.module.com.pojo.vo.district.DistrictTreeVo;
import com.github.lotus.chaos.module.com.service.DistrictService;
import com.github.lotus.chaos.module.lang.manager.LangManager;
import com.github.lotus.chaos.module.lang.pojo.dto.AMapDistrictDto;
import com.github.lotus.chaos.module.com.enums.DistrictLevel;
import in.hocg.boot.mybatis.plus.autoconfiguration.tree.TreeServiceImpl;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.web.datastruct.tree.Tree;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
    private final LangManager langManager;

    @Async
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importByAMapUrl() {
        this.removeAll();
        insertList(null, langManager.getDistrict());
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
    public List<DistrictComplexVo> getChildrenDistrictByAdcode(String adCode) {
        return baseMapper.getChildrenDistrictByAdcode(adCode)
            .stream().map(this::convertComplex)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DistrictComplexVo> getProvince() {
        return LangUtils.toList(selectListByLevel(DistrictLevel.Province), this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DistrictComplexVo> getCity() {
        return LangUtils.toList(selectListByLevel(DistrictLevel.City), this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DistrictComplexVo> getCounty() {
        return LangUtils.toList(selectListByLevel(DistrictLevel.Country), this::convertComplex);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DistrictComplexVo> getDistrict() {
        return LangUtils.toList(selectListByLevel(DistrictLevel.District), this::convertComplex);
    }

    private List<District> selectListByLevel(@NonNull DistrictLevel level) {
        return lambdaQuery().eq(District::getLevel, level.getCode()).list();
    }

    private DistrictComplexVo convertComplex(District entity) {
        return mapping.asDistrictComplexVo(entity);
    }

    private void insertList(Long parentId, List<AMapDistrictDto> dtos) {
        if (Objects.isNull(dtos)) {
            return;
        }
        for (AMapDistrictDto dto : dtos) {
            final District entity = new District();
            entity.setParentId(parentId);
            entity.setCityCode(dto.getCitycode());
            entity.setAdCode(dto.getAdcode());
            entity.setLevel(dto.getDistrictLevel().getCode());
            entity.setTitle(dto.getName());
            entity.setLat(dto.getLat());
            entity.setLng(dto.setLng());
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
