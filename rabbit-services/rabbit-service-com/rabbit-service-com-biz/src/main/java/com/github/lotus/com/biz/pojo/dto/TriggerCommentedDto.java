package com.github.lotus.com.biz.pojo.dto;

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
public class TriggerCommentedDto implements Serializable {
    @ApiModelProperty("被评论")
    private Long beCommentedId;
    @ApiModelProperty("评论")
    private Long commentId;
    @ApiModelProperty("评论人")
    private Long creatorId;
    @ApiModelProperty("评论时间")
    private LocalDateTime createdAt;
}
