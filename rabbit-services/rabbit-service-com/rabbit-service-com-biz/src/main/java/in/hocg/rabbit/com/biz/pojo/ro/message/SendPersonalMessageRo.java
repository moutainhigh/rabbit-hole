package in.hocg.rabbit.com.biz.pojo.ro.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/3/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class SendPersonalMessageRo {
    @NotNull
    @ApiModelProperty(value = "内容", required = true)
    private String content;
    @NotNull
    @ApiModelProperty(value = "接收人", required = true)
    private Long receiver;

    @ApiModelProperty(hidden = true)
    private Long userId;
}
