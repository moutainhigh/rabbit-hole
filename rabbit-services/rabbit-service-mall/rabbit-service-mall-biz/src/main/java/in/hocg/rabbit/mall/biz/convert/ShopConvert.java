package in.hocg.rabbit.mall.biz.convert;

import in.hocg.rabbit.mall.biz.entity.Shop;
import in.hocg.rabbit.mall.biz.mapstruct.ShopMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.ShopComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ShopOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/1/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ShopConvert {
    private final ShopMapping mapping;

    public ShopComplexVo asShopComplexVo(Shop entity) {
        return mapping.asShopComplexVo(entity);
    }

    public ShopOrdinaryVo asShopOrdinaryVo(Shop entity) {
        return mapping.asShopOrdinaryVo(entity);
    }
}
