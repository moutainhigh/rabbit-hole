package in.hocg.rabbit.mina.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/4/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class RechargeProductCompleteRo {
    private String keyword;

    @ApiModelProperty(hidden = true)
    private Long opsUserId;
}
