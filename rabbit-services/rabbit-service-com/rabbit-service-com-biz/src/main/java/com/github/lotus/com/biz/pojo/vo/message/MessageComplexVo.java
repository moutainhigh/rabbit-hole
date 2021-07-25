package com.github.lotus.com.biz.pojo.vo.message;

import com.github.lotus.chaos.api.ChaosNamedApi;
import com.github.lotus.chaos.api.NamedType;
import com.github.lotus.common.datadict.com.MessageUserRefType;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import in.hocg.boot.named.annotation.UseNamedService;
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
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "messageType", type = NamedType.DataDict, args = {MessageUserRefType.KEY})
    private String messageTypeName;
    @ApiModelProperty("接收者")
    private Long receiverUser;
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "receiverUser", type = NamedType.Userid2Nickname)
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
