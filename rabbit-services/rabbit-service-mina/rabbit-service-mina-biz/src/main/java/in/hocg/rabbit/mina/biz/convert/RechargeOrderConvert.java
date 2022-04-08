package in.hocg.rabbit.mina.biz.convert;

import in.hocg.rabbit.mina.api.pojo.vo.RechargeOrderVo;
import in.hocg.rabbit.mina.biz.entity.RechargeOrder;
import in.hocg.rabbit.mina.biz.mapstruct.RechargeMapping;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeOrderOrdinaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RechargeOrderConvert {
    private final RechargeMapping mapping;

    public RechargeOrderOrdinaryVo asRechargeOrderOrdinaryVo(RechargeOrder entity) {
        return mapping.asRechargeOrderOrdinaryVo(entity);
    }

    public RechargeOrderVo asRechargeOrderVo(RechargeOrder entity) {
        return mapping.asRechargeOrderVo(entity);
    }
}
