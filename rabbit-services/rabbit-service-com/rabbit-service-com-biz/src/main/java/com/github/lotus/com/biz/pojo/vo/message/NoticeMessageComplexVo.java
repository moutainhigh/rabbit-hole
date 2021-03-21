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
public class NoticeMessageComplexVo {
    @ApiModelProperty("事件类型")
    private String eventType;
    @ApiModelProperty("订阅对象类型")
    private String refType;
    @ApiModelProperty("订阅对象")
    private RefObject refObject;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;

    @Data
    @ApiModel
    public static class RefObject {
        @ApiModelProperty
        private Long id;
        @ApiModelProperty
        private String title;
    }
}
