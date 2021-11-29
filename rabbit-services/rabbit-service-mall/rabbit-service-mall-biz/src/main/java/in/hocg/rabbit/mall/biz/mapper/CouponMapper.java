package in.hocg.rabbit.mall.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.mall.biz.entity.Coupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.rabbit.mall.biz.pojo.ro.CouponPagingRo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [促销模块] 优惠券表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Mapper
public interface CouponMapper extends BaseMapper<Coupon> {
    IPage<Coupon> paging(CouponPagingRo ro, Page<Object> ofPage);
}
