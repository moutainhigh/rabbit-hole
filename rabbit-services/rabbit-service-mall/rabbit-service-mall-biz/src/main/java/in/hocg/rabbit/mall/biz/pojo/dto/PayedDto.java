package in.hocg.rabbit.mall.biz.pojo.dto;

import in.hocg.rabbit.mall.biz.entity.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/1/25
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class PayedDto {
    @ApiModelProperty("订单")
    private Order order;
    @ApiModelProperty("支付方式")
    private String payWay;
    @ApiModelProperty(value = "完成支付时间", hidden = true)
    private LocalDateTime finishedAt;
}
