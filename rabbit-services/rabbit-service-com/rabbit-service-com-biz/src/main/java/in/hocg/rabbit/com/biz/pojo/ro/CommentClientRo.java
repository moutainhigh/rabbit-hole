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
    @ApiModelProperty
    private Long refId;
    @ApiModelProperty
    private String refType;


    @ApiModelProperty
    private Long id;
    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty(required = true, hidden = true)
    private Long userId;
}
