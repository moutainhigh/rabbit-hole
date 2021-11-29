package in.hocg.rabbit.com.biz.pojo.ro.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by hocgin on 2021/3/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class SendSystemMessageRo {
    @NotNull
    @ApiModelProperty(value = "标题", required = true)
    private String title;
    @NotNull
    @ApiModelProperty(value = "内容", required = true)
    private String content;
    @NotNull
    @Size(min = 1, max = 100)
    @ApiModelProperty(value = "接收人", required = true)
    private List<Long> receiver;

    @ApiModelProperty(hidden = true)
    private Long userId;
}
