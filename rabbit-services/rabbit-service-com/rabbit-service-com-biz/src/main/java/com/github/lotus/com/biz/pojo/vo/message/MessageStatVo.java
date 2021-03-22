package com.github.lotus.com.biz.pojo.vo.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/3/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "消息状态")
public class MessageStatVo {
    @ApiModelProperty("未读总数量")
    private Integer unreadTotalCount;
    @ApiModelProperty("未读系统消息数量")
    private Integer unreadSystemCount;
    @ApiModelProperty("未读私信消息数量")
    private Integer unreadPersonCount;
    @ApiModelProperty("未读通知消息数量")
    private Integer unreadNoticeCount;
}
