package in.hocg.rabbit.com.api.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hocgin on 2022/4/23
 * email: hocgin@gmail.com
 * 概要
 *
 * @author hocgin
 */
@Data
@InjectNamed
public class CommentSummaryVo implements Serializable {
    @ApiModelProperty("最后评论记录列表(晚->早)")
    private List<LastCommentVo> lastReplyList;
    @ApiModelProperty("最后一条评论")
    private LastCommentVo lastReply;

    @ApiModelProperty("总评论数")
    private Long totalReply;

    @Data
    public static class LastCommentVo implements Serializable {
        private Long creator;
        private LocalDateTime createdAt;
    }
}
