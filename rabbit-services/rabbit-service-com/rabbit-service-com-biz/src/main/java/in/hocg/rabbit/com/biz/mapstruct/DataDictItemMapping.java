package in.hocg.rabbit.com.biz.mapstruct;

import in.hocg.rabbit.com.api.pojo.vo.DataDictItemVo;
import in.hocg.rabbit.com.biz.entity.DataDictItem;
import in.hocg.rabbit.com.biz.pojo.ro.DataDictItemInsertRo;
import in.hocg.rabbit.com.biz.pojo.ro.DataDictItemUpdateRo;
import in.hocg.rabbit.com.biz.pojo.vo.DataDictItemComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.DataDictItemOrdinaryVo;
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

    DataDictItemOrdinaryVo asOrdinary(DataDictItem entity);

    @Mapping(target = "priority", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dictId", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    DataDictItem asDataDictItem(DataDictItemInsertRo ro);

    DataDictItemComplexVo asComplex(DataDictItem entity);

    DataDictItem asDataDictItem(DataDictItemUpdateRo ro);
}
