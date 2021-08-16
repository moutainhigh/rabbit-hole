package com.github.lotus.bmw.biz.mapstruct;

import com.github.lotus.bmw.biz.entity.TradeOrder;
import com.github.lotus.bmw.biz.pojo.vo.CashierInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface BmwMapping {

    @Mapping(target = "tradeOrderId", source = "id")
    @Mapping(target = "payTypes", ignore = true)
    @Mapping(target = "paySceneId", ignore = true)
    @Mapping(target = "accessMchName", ignore = true)
    CashierInfoVo asCashierInfoVo(TradeOrder tradeOrder);
}
