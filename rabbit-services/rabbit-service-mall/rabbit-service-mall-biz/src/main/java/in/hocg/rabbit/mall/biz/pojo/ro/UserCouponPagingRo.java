package in.hocg.rabbit.mall.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.PageRo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/8/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class UserCouponPagingRo extends PageRo {
    @ApiModelProperty("拥有人")
    private Long ownerUserId;
}
