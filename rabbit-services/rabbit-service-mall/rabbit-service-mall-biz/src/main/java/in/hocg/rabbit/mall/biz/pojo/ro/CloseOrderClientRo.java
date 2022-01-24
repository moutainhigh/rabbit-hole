package in.hocg.rabbit.mall.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CloseOrderClientRo {
    @ApiModelProperty(value = "操作人", hidden = true)
    private Long operatorId;
}
