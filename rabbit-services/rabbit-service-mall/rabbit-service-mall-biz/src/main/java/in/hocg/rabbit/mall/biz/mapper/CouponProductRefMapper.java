package in.hocg.rabbit.mall.biz.mapper;

import in.hocg.rabbit.mall.biz.entity.CouponProductRef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.rabbit.mall.biz.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [促销模块] 优惠券可用商品表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Mapper
public interface CouponProductRefMapper extends BaseMapper<CouponProductRef> {

    List<Product> listProductCategoryByCouponId(@Param("couponId") Long couponId);
}
