package in.hocg.rabbit.com.biz.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/8/29
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CommentClientVo {
    private Long id;
    @ApiModelProperty("被评论的评论")
    private Long replyId;
    @ApiModelProperty("喜欢数量")
    private Integer likes;
    @ApiModelProperty("不喜欢数量")
    private Integer disliked;
    @ApiModelProperty("登陆用户的点赞行为")
    private String action = Action.none.name();
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("是否有人评论该评论")
    private Boolean hasReply;
    @ApiModelProperty("创建时间")
    private LocalDateTime datetime;
    @ApiModelProperty("评论人")
    private CommentUserVo author;
    @ApiModelProperty("被评论人")
    private CommentUserVo replier;


    public enum Action {
        liked, disliked, none
    }
}
