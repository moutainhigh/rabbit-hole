package in.hocg.rabbit.com.biz.pojo.ro;

import in.hocg.boot.validation.annotation.EnumRange;
import in.hocg.rabbit.com.api.enums.comment.CommentTargetType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class CommentTargetInsertRo {
    @ApiModelProperty("评论对象ID")
    private Long relId;
    @EnumRange(enumClass = CommentTargetType.class, message = "评论对象类型错误")
    @ApiModelProperty("评论对象类型")
    private String relType;
    @ApiModelProperty("启用状态")
    private Boolean enabled;
}
