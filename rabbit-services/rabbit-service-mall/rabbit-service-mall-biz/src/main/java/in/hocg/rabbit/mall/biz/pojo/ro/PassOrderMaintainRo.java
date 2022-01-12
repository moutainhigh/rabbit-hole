package in.hocg.rabbit.mall.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2020/3/28.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class PassOrderMaintainRo {
    @NotNull(message = "处理失败")
    private Boolean isPass;
    private String handleRemark;
    @ApiModelProperty(hidden = true)
    private Long operatorId;
}
