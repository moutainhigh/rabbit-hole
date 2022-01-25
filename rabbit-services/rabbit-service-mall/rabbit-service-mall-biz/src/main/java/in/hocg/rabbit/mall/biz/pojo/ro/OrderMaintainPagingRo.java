package in.hocg.rabbit.mall.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.PageRo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/3/26.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class OrderMaintainPagingRo extends PageRo {
    @ApiModelProperty("订单ID")
    private Long orderId;
    private String keyword;
}
