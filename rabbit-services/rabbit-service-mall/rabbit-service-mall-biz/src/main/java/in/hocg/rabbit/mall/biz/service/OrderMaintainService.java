package in.hocg.rabbit.mall.biz.service;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.mall.biz.entity.OrderMaintain;
import in.hocg.rabbit.mall.biz.pojo.ro.CloseByBuyerRo;
import in.hocg.rabbit.mall.biz.pojo.ro.MaintainClientRo;
import in.hocg.rabbit.mall.biz.pojo.ro.PassOrderMaintainRo;

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
}
