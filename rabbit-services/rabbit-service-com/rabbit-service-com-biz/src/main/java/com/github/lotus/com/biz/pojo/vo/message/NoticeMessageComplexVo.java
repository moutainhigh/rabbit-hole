package com.github.lotus.com.biz.pojo.vo.message;

import com.github.lotus.chaos.api.ChaosNamedApi;
import com.github.lotus.chaos.api.NamedType;
import com.github.lotus.common.datadict.com.NoticeMessageEventType;
import com.github.lotus.common.datadict.com.NoticeMessageRefType;
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
public class NoticeMessageComplexVo {
    @ApiModelProperty("事件类型")
    private String eventType;
    @Named(idFor = "eventType", type = NamedType.DataDict, args = {NoticeMessageEventType.KEY})
    private String eventTypeName;
    @ApiModelProperty("订阅对象类型")
    private String refType;
    @Named(idFor = "refType", type = NamedType.DataDict, args = {NoticeMessageRefType.KEY})
    private String refTypeName;
    @ApiModelProperty("订阅对象")
    private RefObject refObject;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @UseNamedService(ChaosNamedApi.class)
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
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
