package in.hocg.rabbit.mina.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.PageRo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class RechargeOrderPageRo extends PageRo {

    @ApiModelProperty(hidden = true)
    private Long opsUserId;
}
