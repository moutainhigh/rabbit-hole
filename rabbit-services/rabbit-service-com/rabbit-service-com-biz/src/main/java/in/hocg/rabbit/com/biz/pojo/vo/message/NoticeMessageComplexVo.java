package in.hocg.rabbit.com.biz.pojo.vo.message;

import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.rabbit.com.api.enums.NoticeMessageEventType;
import in.hocg.rabbit.com.api.enums.NoticeMessageRefType;
import in.hocg.boot.named.annotation.InjectNamed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@InjectNamed
public class NoticeMessageComplexVo {
    @ApiModelProperty("事件类型")
    private String eventType;
    @ChaosNamed(idFor = "eventType", type = ChaosNamedType.DataDictName, args = {NoticeMessageEventType.KEY})
    private String eventTypeName;
    @ApiModelProperty("订阅对象类型")
    private String refType;
    @ChaosNamed(idFor = "refType", type = ChaosNamedType.DataDictName, args = {NoticeMessageRefType.KEY})
    private String refTypeName;
    @ApiModelProperty("订阅对象")
    private RefObject refObject;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2Nickname)
    private String creatorName;

    @Data
    @ApiModel
    public static class RefObject {
        @ApiModelProperty
        private Long id;
        @ApiModelProperty
        private String title;
    }
}
