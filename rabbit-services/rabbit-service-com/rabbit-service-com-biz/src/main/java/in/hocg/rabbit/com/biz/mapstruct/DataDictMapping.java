package in.hocg.rabbit.com.biz.mapstruct;

import in.hocg.rabbit.com.biz.entity.DataDict;
import in.hocg.rabbit.com.biz.pojo.ro.DataDictInsertRo;
import in.hocg.rabbit.com.biz.pojo.ro.DataDictUpdateRo;
import in.hocg.rabbit.com.biz.pojo.vo.DataDictComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.DataDictOrdinaryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/8/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface DataDictMapping {
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "items", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    DataDictComplexVo asComplex(DataDict entity);

    DataDictOrdinaryVo asOrdinary(DataDict entity);

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    DataDict asDataDict(DataDictInsertRo ro);

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    DataDict asDataDict(DataDictUpdateRo ro);
}
