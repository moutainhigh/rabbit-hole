package com.github.lotus.pay.biz.mapstruct;

import com.github.lotus.pay.biz.entity.RefundRecord;
import com.github.lotus.pay.biz.pojo.ro.GoRefundRo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface RefundRecordMapping {
    RefundRecord asRefundRecord(GoRefundRo ro);
}
