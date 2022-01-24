package in.hocg.rabbit.mall.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.PageRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/8/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class OrderPagingRo extends PageRo {
    private Long ownerUserId;
    @ApiModelProperty(value = "订单状态", allowableValues = "create,success,close")
    private String orderStatus;
}
