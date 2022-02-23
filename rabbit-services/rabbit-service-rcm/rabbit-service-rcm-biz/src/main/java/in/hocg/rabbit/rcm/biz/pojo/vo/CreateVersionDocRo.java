package in.hocg.rabbit.rcm.biz.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/2/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CreateVersionDocRo {
    @ApiModelProperty("标题")
    private String title;
}
