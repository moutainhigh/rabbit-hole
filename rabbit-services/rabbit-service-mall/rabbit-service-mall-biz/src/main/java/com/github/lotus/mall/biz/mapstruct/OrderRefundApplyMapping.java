package com.github.lotus.mall.biz.mapstruct;

import com.github.lotus.mall.biz.entity.OrderRefundApply;
import com.github.lotus.mall.biz.pojo.ro.RefundApplyRo;
import com.github.lotus.mall.biz.pojo.vo.OrderRefundApplyComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface OrderRefundApplyMapping {
    @Mapping(target = "receiverName", ignore = true)
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "handlerName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    OrderRefundApplyComplexVo asOrderRefundApplyComplexVo(OrderRefundApply entity);

    @Mapping(target = "refundQuantity", ignore = true)
    @Mapping(target = "refundApplyNo", ignore = true)
    @Mapping(target = "refundAmt", ignore = true)
    @Mapping(target = "receiverId", ignore = true)
    @Mapping(target = "receiveRemark", ignore = true)
    @Mapping(target = "receiveAt", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "handlerId", ignore = true)
    @Mapping(target = "handleRemark", ignore = true)
    @Mapping(target = "handleAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "applyStatus", ignore = true)
    OrderRefundApply asOrderRefundApply(RefundApplyRo ro);
}
