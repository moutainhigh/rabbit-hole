package com.github.lotus.com.biz.mapper;

import com.github.lotus.com.biz.entity.DataDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.com.biz.entity.DataDictItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * [基础模块] 数据字典表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Mapper
public interface DataDictMapper extends BaseMapper<DataDict> {

    List<DataDictItem> listDataDictItemByDictIdAndCode(@Param("typeCode") String typeCode, @Param("itemCodes") List<String> itemCodes);

    List<DataDictItem> getDataDictItemByDictIdAndCode(@Param("typeCode") String typeCode, @Param("itemCode") String itemCode);

    List<DataDictItem> listDataDictItemByCodeAndEnabled(@Param("typeCode") String typeCode, @Param("enabled") Serializable enabled);

}
