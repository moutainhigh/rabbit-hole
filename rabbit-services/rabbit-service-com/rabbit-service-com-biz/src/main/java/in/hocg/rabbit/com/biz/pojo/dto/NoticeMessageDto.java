package in.hocg.rabbit.com.biz.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/4/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@Setter
@Accessors(chain = true)
public class NoticeMessageDto implements Serializable {
    @ApiModelProperty("事件类型")
    private String eventType;
    @ApiModelProperty("订阅对象")
    private Long refId;
    @ApiModelProperty("订阅对象类型")
    private String refType;
    @ApiModelProperty("触发用户")
    private Long triggerUserId;
    @ApiModelProperty("触发时间")
    private LocalDateTime createdAt;
}
