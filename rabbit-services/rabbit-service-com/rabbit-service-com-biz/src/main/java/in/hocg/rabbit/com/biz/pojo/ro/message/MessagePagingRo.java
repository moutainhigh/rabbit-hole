package in.hocg.rabbit.com.biz.pojo.ro.message;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.PageRo;
import in.hocg.boot.validation.annotation.EnumRange;
import in.hocg.rabbit.com.api.enums.message.MessageType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class MessagePagingRo extends PageRo {

    @ApiModelProperty("标记已读(默认:true)")
    private Boolean markReady;

    @EnumRange(enumClass = MessageType.class)
    @ApiModelProperty("消息类型")
    private String messageType;

    @ApiModelProperty(hidden = true)
    private Long receiverUser;
}
