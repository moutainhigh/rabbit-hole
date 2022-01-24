package in.hocg.rabbit.mall.biz.support.order;

import in.hocg.rabbit.mall.api.enums.order.OrderedSource;
import in.hocg.rabbit.mall.biz.support.mode.OrderContext;
import in.hocg.rabbit.mall.biz.support.mode.def.DefaultOrderContext;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/1/18
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class MallOrderContext extends DefaultOrderContext {
    @ApiModelProperty("订单来源")
    private OrderedSource source = OrderedSource.Unknown;
}
