package in.hocg.rabbit.mina.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class BaseRechargeRo {
    @ApiModelProperty(value = "商户用户", hidden = true)
    private Long userId;
}
