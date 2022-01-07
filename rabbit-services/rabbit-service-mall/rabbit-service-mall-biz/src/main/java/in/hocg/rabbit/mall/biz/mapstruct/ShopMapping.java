package in.hocg.rabbit.mall.biz.mapstruct;

import in.hocg.rabbit.mall.biz.entity.Shop;
import in.hocg.rabbit.mall.biz.pojo.ro.ShopSaveRo;
import in.hocg.rabbit.mall.biz.pojo.vo.ShopComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ShopOrdinaryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2022/1/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface ShopMapping {
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Shop asShop(ShopSaveRo ro);

    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    ShopComplexVo asShopComplexVo(Shop entity);

    ShopOrdinaryVo asShopOrdinaryVo(Shop entity);
}
