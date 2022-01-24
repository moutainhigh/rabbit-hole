package in.hocg.rabbit.mall.biz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.mall.biz.convert.ProductConvert;
import in.hocg.rabbit.mall.biz.convert.ShopConvert;
import in.hocg.rabbit.mall.biz.entity.Shop;
import in.hocg.rabbit.mall.biz.mapper.ShopMapper;
import in.hocg.rabbit.mall.biz.mapstruct.ShopMapping;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductCompleteRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ShopCompleteRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ShopPagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ShopSaveRo;
import in.hocg.rabbit.mall.biz.pojo.vo.ShopComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ShopOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.ShopService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [商品模块] 店铺表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-11
 */
@Service
@UseConvert(ShopConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ShopServiceImpl extends AbstractServiceImpl<ShopMapper, Shop> implements ShopService {
    private final ShopMapping mapping;
    private final ShopConvert convert;

    @Override
    public void delete(DeleteRo ro) {
        removeByIds(ro.getId());
    }

    @Override
    public void updateOne(Long id, ShopSaveRo ro) {
        saveOne(id, ro);
    }

    @Override
    public void insertOne(ShopSaveRo ro) {
        saveOne(null, ro);
    }

    private void saveOne(Long id, ShopSaveRo ro) {
        Shop entity = mapping.asShop(ro);
        entity.setId(id);
        saveOrUpdate(entity);
    }

    @Override
    public ShopComplexVo getComplex(Long id) {
        return convert.asShopComplexVo(getById(id));
    }

    @Override
    public IPage<ShopOrdinaryVo> paging(ShopPagingRo ro) {
        return baseMapper.paging(ro, ro.ofPage()).convert(convert::asShopOrdinaryVo);
    }

    @Override
    public List<ShopOrdinaryVo> complete(ShopCompleteRo ro) {
        return LangUtils.toList(baseMapper.complete(ro, ro.ofPage()), convert::asShopOrdinaryVo);
    }
}
