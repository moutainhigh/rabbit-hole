package com.github.lotus.com.biz.mapstruct;

import com.github.lotus.com.api.pojo.vo.DataDictItemVo;
import com.github.lotus.com.biz.entity.DataDictItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/8/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface DataDictItemMapping {
    DataDictItemVo asDataDictItemVo(DataDictItem entity);
}
