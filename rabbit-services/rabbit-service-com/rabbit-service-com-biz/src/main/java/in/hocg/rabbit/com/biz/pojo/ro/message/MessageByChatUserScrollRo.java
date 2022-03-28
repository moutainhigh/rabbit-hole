package in.hocg.rabbit.com.biz.pojo.ro.message;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.ScrollRo;
import in.hocg.boot.validation.annotation.EnumRange;
import in.hocg.rabbit.com.api.enums.message.MessageType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/3/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class MessageByChatUserScrollRo extends ScrollRo {
    @ApiModelProperty("标记已读")
    private Boolean markReady;

    @EnumRange(enumClass = MessageType.class)
    @ApiModelProperty("消息类型")
    private String messageType;

    @ApiModelProperty(value = "相关人")
    private Long chatUserId;

    @ApiModelProperty(value = "登陆人", hidden = true)
    private Long optUserId;

}
