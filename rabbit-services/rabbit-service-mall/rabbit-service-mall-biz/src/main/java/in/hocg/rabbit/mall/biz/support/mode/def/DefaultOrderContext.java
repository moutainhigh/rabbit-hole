package in.hocg.rabbit.mall.biz.support.mode.def;

import in.hocg.rabbit.mall.biz.support.mode.OrderContext;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/1/18
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class DefaultOrderContext extends OrderContext {
    @ApiModelProperty("订单创建时间")
    private LocalDateTime createdAt = LocalDateTime.now();
}
