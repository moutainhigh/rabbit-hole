package in.hocg.rabbit.com.biz.pojo.vo.message;

import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.boot.named.annotation.InjectNamed;

import in.hocg.rabbit.com.api.named.ComDataDictKeys;
import in.hocg.rabbit.com.api.named.ComNamed;
import in.hocg.rabbit.common.datadict.common.RefType;
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
public class MessageComplexVo {
    private Long id;
    @ApiModelProperty("消息类型")
    private String messageType;
    @ComNamed(idFor = "messageType", type = ChaosNamedType.DataDictName, args = {ComDataDictKeys.MMS_MESSAGE_USER_REF_TYPE})
    private String messageTypeName;
    @ApiModelProperty("接收者")
    private Long receiverUser;
    @ChaosNamed(idFor = "receiverUser", type = ChaosNamedType.Userid2Nickname)
    private String receiverUserName;

    @ApiModelProperty("读取时间")
    private LocalDateTime readAt;

    @ApiModelProperty("订阅消息")
    private NoticeMessageComplexVo noticeMessage;
    @ApiModelProperty("系统消息")
    private SystemMessageComplexVo systemMessage;
    @ApiModelProperty("私信消息")
    private PersonalMessageComplexVo personalMessage;

}
