package com.github.lotus.pay.biz.mapstruct;

import com.github.lotus.pay.biz.entity.PayRecord;
import com.github.lotus.pay.biz.pojo.vo.PayRecordOrdinaryVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/1/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface PayRecordMapping {
    PayRecordOrdinaryVo asOrdinary(PayRecord entity);
}
