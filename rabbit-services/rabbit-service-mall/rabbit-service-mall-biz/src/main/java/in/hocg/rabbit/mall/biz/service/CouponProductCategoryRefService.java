package in.hocg.rabbit.mall.biz.service;

import in.hocg.rabbit.mall.biz.entity.CouponProductCategoryRef;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductCategoryComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [促销模块] 优惠券可用商品品类表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
public interface CouponProductCategoryRefService extends AbstractService<CouponProductCategoryRef> {

    List<ProductCategoryComplexVo> listComplexByCouponId(Long couponId);
}
