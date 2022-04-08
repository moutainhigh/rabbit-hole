package in.hocg.rabbit.mina.biz.mapstruct;

import in.hocg.rabbit.mina.api.pojo.ro.RechargeOrderRo;
import in.hocg.rabbit.mina.api.pojo.vo.RechargeOrderVo;
import in.hocg.rabbit.mina.biz.entity.RechargeAccount;
import in.hocg.rabbit.mina.biz.entity.RechargeOrder;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeAccountVo;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeOrderOrdinaryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface RechargeMapping {
    @Mapping(target = "nextNotifyAt", ignore = true)
    @Mapping(target = "finishNotifyAt", ignore = true)
    @Mapping(target = "totalAmt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "orderNo", ignore = true)
    @Mapping(target = "notifyRecount", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "lastNotifyResult", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "failReason", ignore = true)
    @Mapping(target = "creator", source = "userId")
    @Mapping(target = "createdAt", ignore = true)
    RechargeOrder asRechargeOrder(RechargeOrderRo ro);

    @Mapping(target = "userId", source = "creator")
    @Mapping(target = "productAmt", source = "totalAmt")
    RechargeOrderVo asRechargeOrderVo(RechargeOrder entity);

    RechargeOrderOrdinaryVo asRechargeOrderOrdinaryVo(RechargeOrder entity);

    @Mapping(target = "userId", source = "ownerUserId")
    RechargeAccountVo asRechargeAccount(RechargeAccount account);
}
