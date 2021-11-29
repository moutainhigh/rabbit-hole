package in.hocg.rabbit.com.biz.pojo.ro;


import in.hocg.boot.validation.autoconfigure.core.annotation.EnumRange;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by hocgin on 2020/3/8.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CommentUpdateRo {
    @NotEmpty(message = "评论内容不能为空")
    @ApiModelProperty(value = "评论内容", required = true)
    private String content;
    @ApiModelProperty("启用状态")
    private Boolean enabled;

    @ApiModelProperty(required = true, hidden = true)
    private Long userId;
}
