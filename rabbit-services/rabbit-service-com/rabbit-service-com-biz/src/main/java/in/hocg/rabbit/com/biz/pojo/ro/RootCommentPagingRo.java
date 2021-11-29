package in.hocg.rabbit.com.biz.pojo.ro;

import in.hocg.rabbit.common.datadict.com.CommentTargetType;
import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import in.hocg.boot.validation.autoconfigure.core.annotation.EnumRange;
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
     * @see CommentTargetType 的名称
     */
    @ApiModelProperty("评论对象")
    @EnumRange(enumClass = CommentTargetType.class, message = "评论对象类型")
    private String refType;
    @ApiModelProperty("评论对象的原ID,如:文章ID")
    private Long refId;
}
