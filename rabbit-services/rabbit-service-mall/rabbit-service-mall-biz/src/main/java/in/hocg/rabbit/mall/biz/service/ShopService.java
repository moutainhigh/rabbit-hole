package in.hocg.rabbit.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.rabbit.mall.biz.entity.Shop;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.mall.biz.pojo.ro.ProductCompleteRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ShopCompleteRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ShopPagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ShopSaveRo;
import in.hocg.rabbit.mall.biz.pojo.vo.ShopComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ShopOrdinaryVo;

import java.util.List;

/**
 * <p>
 * [商品模块] 店铺表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-11
 */
public interface ShopService extends AbstractService<Shop> {

    void delete(DeleteRo ro);

    void updateOne(Long id, ShopSaveRo ro);

    void insertOne(ShopSaveRo ro);

    ShopComplexVo getComplex(Long id);

    IPage<ShopOrdinaryVo> paging(ShopPagingRo ro);

    List<ShopOrdinaryVo> complete(ShopCompleteRo ro);
}
