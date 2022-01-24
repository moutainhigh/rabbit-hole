package in.hocg.rabbit.mall.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.mall.biz.entity.OrderMaintain;
import in.hocg.rabbit.mall.biz.pojo.ro.CloseByBuyerRo;
import in.hocg.rabbit.mall.biz.pojo.ro.MaintainClientRo;
import in.hocg.rabbit.mall.biz.pojo.ro.OrderMaintainPagingRo;
import in.hocg.rabbit.mall.biz.pojo.ro.PassOrderMaintainRo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderMaintainComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.OrderMaintainOrdinaryVo;

/**
 * <p>
 * [订单模块] 退货申请 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
public interface OrderMaintainService extends AbstractService<OrderMaintain> {

    void create(Long id, MaintainClientRo ro);

    void pass(Long id, PassOrderMaintainRo ro);

    void closeByBuyer(Long id, CloseByBuyerRo ro);

    void closeBySeller(Long id);

    IPage<OrderMaintainOrdinaryVo> paging(OrderMaintainPagingRo ro);

    OrderMaintainComplexVo getComplex(Long id);
}
