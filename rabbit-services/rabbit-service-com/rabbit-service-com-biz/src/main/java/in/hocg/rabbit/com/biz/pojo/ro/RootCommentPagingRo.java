package in.hocg.rabbit.com.biz.pojo.ro;

import in.hocg.rabbit.com.api.enums.comment.CommentTargetType;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.PageRo;
import in.hocg.boot.validation.annotation.EnumRange;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2020/3/8.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RootCommentPagingRo extends PageRo {
    /**
     * @see CommentTargetType 评论对象类型
     */
    @ApiModelProperty(value = "评论对象", hidden = true)
    private String refType;
    @ApiModelProperty(value = "评论对象的原ID,如:文章ID", hidden = true)
    private Long refId;
}
