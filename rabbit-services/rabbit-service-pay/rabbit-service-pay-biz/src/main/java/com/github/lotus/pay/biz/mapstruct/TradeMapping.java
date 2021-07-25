package com.github.lotus.pay.biz.mapstruct;

import com.github.lotus.pay.api.pojo.ro.CreateTradeRo;
import com.github.lotus.pay.biz.entity.Trade;
import com.github.lotus.pay.biz.pojo.vo.TradeComplexVo;
import com.github.lotus.pay.api.pojo.vo.TradeOrdinaryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface TradeMapping {
    default Trade asTrade(CreateTradeRo ro) {
        Trade entity = new Trade();
        entity.setOutTradeSn(ro.getOutTradeSn());
        entity.setNotifyUrl(ro.getNotifyUrl());
        entity.setCreatedIp(ro.getClientIp());
        return entity;
    }

    @Mapping(target = "tradeStatusName", ignore = true)
    @Mapping(target = "payModeName", ignore = true)
    @Mapping(target = "accessAppName", ignore = true)
    TradeComplexVo asComplex(Trade entity);

    TradeOrdinaryVo asOrdinary(Trade entity);
}
