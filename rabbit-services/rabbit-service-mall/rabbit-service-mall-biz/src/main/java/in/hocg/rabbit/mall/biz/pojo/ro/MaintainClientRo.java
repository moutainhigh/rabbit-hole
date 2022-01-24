package in.hocg.rabbit.mall.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2022/1/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class MaintainClientRo {
    @NotNull(message = "退货原因不能为空")
    @ApiModelProperty("退货原因")
    private String refundReason;
    @NotNull(message = "备注不能为空")
    @ApiModelProperty("备注")
    private String refundRemark;

    @ApiModelProperty(value = "操作人", hidden = true)
    private Long operatorId;
}
