package in.hocg.rabbit.mall.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
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
@ApiModel
public class PayedOrderBySellerRo {
    @ApiModelProperty(value = "操作人", hidden = true)
    private Long operatorId;
}
