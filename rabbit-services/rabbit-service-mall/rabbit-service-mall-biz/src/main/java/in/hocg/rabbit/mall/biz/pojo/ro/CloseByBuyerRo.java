package in.hocg.rabbit.mall.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/1/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CloseByBuyerRo {

    @ApiModelProperty(value = "操作人", hidden = true)
    private Long operatorId;
}
