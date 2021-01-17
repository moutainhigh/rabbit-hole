package com.github.lotus.com.biz.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2020/3/8.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CommentComplexVo {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("评论内容")
    private String content;
    @ApiModelProperty("评论者")
    private Long commenterId;
    private CommentUserVo commenter;
    @ApiModelProperty("被评论者")
    private Long pCommenterId;
    private CommentUserVo pCommenter;
    @ApiModelProperty("评论时间")
    private LocalDateTime createdAt;
}
