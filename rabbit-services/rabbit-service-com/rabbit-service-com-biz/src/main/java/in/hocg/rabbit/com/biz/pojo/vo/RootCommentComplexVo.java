package in.hocg.rabbit.com.biz.pojo.vo;

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
public class RootCommentComplexVo extends CommentComplexVo {
    @ApiModelProperty(value = "子评论条数")
    private Long childTotal = 0L;
}
