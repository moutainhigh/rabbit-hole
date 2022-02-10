package in.hocg.rabbit.com.biz.pojo.ro.comment;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2022/2/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class CommentReportRo {
    @NotNull(message = "评论ID不能为空")
    private Long commentId;
    private Long userId;
}
