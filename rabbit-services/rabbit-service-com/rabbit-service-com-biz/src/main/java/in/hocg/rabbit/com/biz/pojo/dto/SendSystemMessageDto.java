package in.hocg.rabbit.com.biz.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class SendSystemMessageDto {
    @ApiModelProperty(value = "标题", required = true)
    private String title;
    @ApiModelProperty(value = "内容", required = true)
    private String content;
    @ApiModelProperty(value = "创建者", required = true)
    private Long creator;
    @ApiModelProperty(value = "接收人", required = true)
    private List<Long> receiver;
}
