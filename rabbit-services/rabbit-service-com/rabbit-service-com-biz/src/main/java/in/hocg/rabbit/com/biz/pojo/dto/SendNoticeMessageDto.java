package in.hocg.rabbit.com.biz.pojo.dto;

import in.hocg.rabbit.com.api.enums.NoticeMessageEventType;
import in.hocg.rabbit.com.api.enums.NoticeMessageRefType;
import in.hocg.boot.validation.annotation.EnumRange;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class SendNoticeMessageDto {
    @ApiModelProperty(value = "事件类型", required = true)
    @EnumRange(enumClass = NoticeMessageEventType.class)
    private String eventType;
    @ApiModelProperty(value = "订阅对象类型", required = true)
    @EnumRange(enumClass = NoticeMessageRefType.class)
    private String refType;
    @ApiModelProperty(value = "订阅对象", required = true)
    private Long refId;
    @ApiModelProperty(value = "创建者", required = true)
    private Long creator;
    @ApiModelProperty(value = "接收人", required = true)
    private Long receiver;
}
