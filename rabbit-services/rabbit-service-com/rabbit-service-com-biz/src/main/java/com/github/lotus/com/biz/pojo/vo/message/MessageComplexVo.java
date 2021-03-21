package com.github.lotus.com.biz.pojo.vo.message;

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
public class MessageComplexVo {
    private Long id;
    @ApiModelProperty("消息类型")
    private String messageType;
    @ApiModelProperty("接收者")
    private Long receiverUser;
    @ApiModelProperty("读取时间")
    private LocalDateTime readAt;

    @ApiModelProperty("订阅消息")
    private NoticeMessageComplexVo noticeMessage;
    @ApiModelProperty("系统消息")
    private SystemMessageComplexVo systemMessage;
    @ApiModelProperty("私信消息")
    private PersonalMessageComplexVo personalMessage;

}
