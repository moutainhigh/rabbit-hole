package in.hocg.rabbit.com.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/8/29
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CommentClientRo {
    @ApiModelProperty(hidden = true, value = "评论目标ID", required = true)
    private Long refId;
    @ApiModelProperty(hidden = true, value = "评论目标类型", required = true)
    private String refType;


    @ApiModelProperty("如果评论的目标是评论")
    private Long commentId;
    @ApiModelProperty("评论内容")
    private String content;
    @ApiModelProperty(value = "操作人", hidden = true)
    private Long optUserId;
}
