package in.hocg.rabbit.mall.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.mall.biz.entity.StintRule;
import in.hocg.rabbit.mall.biz.pojo.ro.StintRuleCompleteRo;
import in.hocg.rabbit.mall.biz.pojo.ro.StintRulePagingRo;
import in.hocg.rabbit.mall.biz.pojo.vo.StintRuleOrdinaryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [促销模块] 限制规则表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Mapper
public interface StintRuleMapper extends BaseMapper<StintRule> {

    IPage<StintRule> paging(@Param("ro") StintRulePagingRo ro, Page<Object> ofPage);

    IPage<StintRule> complete(@Param("ro") StintRuleCompleteRo ro, Page<Object> ofPage);

    List<StintRule> listByCouponId(@Param("couponId") Long couponId);
}
