package in.hocg.rabbit.rcm.biz.pojo.ro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/2/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class PushDocContentRo {
    @ApiModelProperty("文档类型")
    private String doctype;
    @ApiModelProperty("文本")
    private String content;
}
