package in.hocg.rabbit.mall.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.mall.biz.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.rabbit.mall.biz.pojo.ro.ShopCompleteRo;
import in.hocg.rabbit.mall.biz.pojo.ro.ShopPagingRo;
import in.hocg.rabbit.mall.biz.pojo.vo.ShopOrdinaryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [商品模块] 店铺表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-01-11
 */
@Mapper
public interface ShopMapper extends BaseMapper<Shop> {

    IPage<Shop> paging(@Param("ro") ShopPagingRo ro, Page<Object> ofPage);

    List<Shop> complete(@Param("ro") ShopCompleteRo ro, Page<Object> ofPage);
}
