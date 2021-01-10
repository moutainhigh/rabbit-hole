package com.github.lotus.com.biz.service.impl;

import com.github.lotus.com.api.pojo.vo.DataDictItemVo;
import com.github.lotus.com.biz.entity.DataDict;
import com.github.lotus.com.biz.entity.DataDictItem;
import com.github.lotus.com.biz.mapper.DataDictMapper;
import com.github.lotus.com.biz.mapstruct.DataDictItemMapping;
import com.github.lotus.com.biz.service.DataDictItemService;
import com.github.lotus.com.biz.service.DataDictService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.mybatis.plus.autoconfiguration.utils.Enabled;
import in.hocg.boot.utils.LangUtils;
import in.hocg.boot.web.datastruct.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * [基础模块] 数据字典表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DataDictServiceImpl extends AbstractServiceImpl<DataDictMapper, DataDict>
    implements DataDictService {
    private final DataDictItemService dataDictItemService;
    private final DataDictItemMapping dataDictItemMapping;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<KeyValue> listDataDictItemDtoByDictIdAndCode(String typeCode, List<String> itemCodes) {
        return this.listDataDictItemByDictIdAndCode(typeCode, itemCodes).stream()
            .map(this::convertKeyValue)
            .collect(Collectors.toList());
    }

    @Override
    public List<DataDictItemVo> listDataDictItemVoDtoByDictIdAndCode(String typeCode, List<String> itemCodes) {
        return LangUtils.toList(listDataDictItemByDictIdAndCode(typeCode, itemCodes), dataDictItemMapping::asDataDictItemVo);
    }

    public List<DataDictItem> listDataDictItemByDictIdAndCode(String typeCode, List<String> itemCodes) {
        return baseMapper.listDataDictItemByDictIdAndCode(typeCode, itemCodes);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<KeyValue> listKeyValueByCodeAndEnabledIsOn(String typeCode) {
        return this.listKeyValueByCodeAndEnabled(typeCode, (String) Enabled.On.getCode());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<KeyValue> listKeyValueByCode(String typeCode) {
        return this.listKeyValueByCodeAndEnabled(typeCode, null);
    }

    private List<KeyValue> listKeyValueByCodeAndEnabled(String typeCode, String enabled) {
        return listDataDictItemByCodeAndEnabled(typeCode, enabled)
            .stream().map(this::convertKeyValue)
            .collect(Collectors.toList());
    }

    private KeyValue convertKeyValue(DataDictItem item) {
        return new KeyValue()
            .setValue(item.getCode())
            .setKey(item.getTitle());
    }

    private List<DataDictItem> listDataDictItemByCodeAndEnabled(String typeCode, String enabled) {
        return baseMapper.listDataDictItemByCodeAndEnabled(typeCode, enabled);
    }
}
