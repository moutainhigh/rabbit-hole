package in.hocg.rabbit.rcm.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/2/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class CreateVersionDocRo {
    @ApiModelProperty("标题")
    private String title;
}
