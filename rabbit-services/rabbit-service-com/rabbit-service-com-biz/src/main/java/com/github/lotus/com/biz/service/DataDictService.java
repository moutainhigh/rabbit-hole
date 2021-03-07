package com.github.lotus.com.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.api.pojo.vo.DataDictItemVo;
import com.github.lotus.com.biz.entity.DataDict;
import com.github.lotus.com.biz.pojo.ro.DataDictInsertRo;
import com.github.lotus.com.biz.pojo.ro.DataDictPagingRo;
import com.github.lotus.com.biz.pojo.ro.DataDictUpdateRo;
import com.github.lotus.com.biz.pojo.vo.DataDictComplexVo;
import com.github.lotus.com.biz.pojo.vo.DataDictOrdinaryVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;
import in.hocg.boot.web.datastruct.KeyValue;

import java.util.List;

/**
 * <p>
 * [基础模块] 数据字典表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
public interface DataDictService extends AbstractService<DataDict> {

    List<KeyValue> listDataDictItemDtoByDictIdAndCode(String typeCode, List<String> itemCodes);

    List<DataDictItemVo> listDataDictItemVoDtoByDictIdAndCode(String typeCode, List<String> itemCodes);

    /**
     * 查询启用的数据字典项
     *
     * @param typeCode 数据字典类型
     * @return []
     */
    List<KeyValue> listKeyValueByCodeAndEnabledIsOn(String typeCode);

    List<KeyValue> listKeyValueByCode(String code);

    DataDictComplexVo getComplex(Long id);

    void delete(Long id);

    void insertOne(DataDictInsertRo ro);

    void updateOne(Long id, DataDictUpdateRo ro);

    IPage<DataDictOrdinaryVo> paging(DataDictPagingRo ro);
}
