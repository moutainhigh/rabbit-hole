package in.hocg.rabbit.mall.biz.pojo.ro;

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
public class CloseOrderManageRo {
    @ApiModelProperty(value = "操作人", hidden = true)
    private Long operatorId;
}
