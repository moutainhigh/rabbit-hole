package in.hocg.rabbit.mina.biz.mapstruct;

import in.hocg.rabbit.mina.biz.entity.AppCard;
import in.hocg.rabbit.mina.biz.pojo.ro.AppCardSaveRo;
import in.hocg.rabbit.mina.biz.pojo.vo.AppCardComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.MinaAppCardComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.AppCardOrdinaryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/1/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface AppCardMapping {

    @Mapping(target = "viewUrls", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "href", ignore = true)
    MinaAppCardComplexVo asAppComplexVo(AppCard entity);

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    AppCard asAppCard(AppCardSaveRo ro);

    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    AppCardOrdinaryVo asOrdinary(AppCard entity);

    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    AppCardComplexVo asComplex(AppCard entity);
}
