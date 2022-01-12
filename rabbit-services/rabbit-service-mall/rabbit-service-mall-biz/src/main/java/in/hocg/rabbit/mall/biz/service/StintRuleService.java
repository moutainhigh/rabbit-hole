package in.hocg.rabbit.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.mall.biz.entity.StintRule;
import in.hocg.rabbit.mall.biz.pojo.ro.StintRuleCompleteRo;
import in.hocg.rabbit.mall.biz.pojo.ro.StintRulePagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.StintRuleSaveRo;
import in.hocg.rabbit.mall.biz.pojo.vo.StintRuleComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.StintRuleOrdinaryVo;

import java.util.List;

/**
 * <p>
 * [促销模块] 限制规则表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
public interface StintRuleService extends AbstractService<StintRule> {

    void insertOne(StintRuleSaveRo ro);

    void updateOne(Long id, StintRuleSaveRo ro);

    void delete(DeleteRo ro);

    StintRuleComplexVo getComplex(Long id);

    IPage<StintRuleOrdinaryVo> paging(StintRulePagingRo ro);

    List<StintRuleOrdinaryVo> complete(StintRuleCompleteRo ro);

    List<StintRule> listByCouponId(Long couponId);
}
