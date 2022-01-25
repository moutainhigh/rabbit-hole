package in.hocg.rabbit.com.biz.pojo.vo.message;

import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.boot.named.annotation.InjectNamed;

import in.hocg.rabbit.com.api.named.ComDataDictKeys;
import in.hocg.rabbit.com.api.named.ComNamed;
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
    @ComNamed(idFor = "eventType", type = ChaosNamedType.DataDictName, args = {ComDataDictKeys.MMS_NOTICE_MESSAGE_EVENT_TYPE})
    private String eventTypeName;
    @ApiModelProperty("订阅对象类型")
    private String refType;
    @ComNamed(idFor = "refType", type = ChaosNamedType.DataDictName, args = {ComDataDictKeys.MMS_NOTICE_MESSAGE_REF_TYPE})
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
