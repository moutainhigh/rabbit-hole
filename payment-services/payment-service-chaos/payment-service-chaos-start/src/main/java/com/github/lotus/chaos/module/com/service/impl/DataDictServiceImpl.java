package com.github.lotus.chaos.module.com.service.impl;

import com.github.lotus.chaos.module.com.entity.DataDict;
import com.github.lotus.chaos.module.com.entity.DataDictItem;
import com.github.lotus.chaos.module.com.mapper.DataDictMapper;
import com.github.lotus.chaos.module.com.service.DataDictItemService;
import com.github.lotus.chaos.module.com.service.DataDictService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.mybatis.plus.autoconfiguration.utils.Enabled;
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
            .stream().map(item -> new KeyValue()
                .setValue(item.getCode())
                .setKey(item.getTitle()))
            .collect(Collectors.toList());
    }

    private List<DataDictItem> listDataDictItemByCodeAndEnabled(String typeCode, String enabled) {
        return baseMapper.listDataDictItemByCodeAndEnabled(typeCode, enabled);
    }
}
