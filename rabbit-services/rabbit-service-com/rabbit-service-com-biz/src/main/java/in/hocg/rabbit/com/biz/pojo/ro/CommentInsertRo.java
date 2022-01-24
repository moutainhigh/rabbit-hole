package in.hocg.rabbit.com.biz.pojo.ro;

import in.hocg.rabbit.com.api.enums.CommentTargetType;
import in.hocg.boot.validation.annotation.EnumRange;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2020/3/8.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CommentInsertRo {
    /**
     * @see CommentTargetType 的名称
     */
    @NotNull
    @EnumRange(enumClass = CommentTargetType.class, message = "评论对象类型")
    @ApiModelProperty("评论对象")
    private String refType;
    @NotNull
    @ApiModelProperty("评论对象的原ID,如:文章ID")
    private Long refId;
    @ApiModelProperty("评论父级ID")
    private Long parentId;
    @NotEmpty(message = "评论内容不能为空")
    @ApiModelProperty(value = "评论内容", required = true)
    private String content;

    @ApiModelProperty(required = true, hidden = true)
    private Long userId;
}
