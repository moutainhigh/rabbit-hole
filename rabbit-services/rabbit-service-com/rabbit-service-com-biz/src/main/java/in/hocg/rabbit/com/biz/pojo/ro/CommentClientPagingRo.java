package in.hocg.rabbit.com.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.PageRo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/8/29
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CommentClientPagingRo extends PageRo {
    @ApiModelProperty("上级评论")
    private Long parentId;


    @ApiModelProperty(value = "评论对象", hidden = true)
    private Long targetId;
    @ApiModelProperty(value = "对象", hidden = true)
    private Long refId;
    @ApiModelProperty(value = "对象类型",hidden = true)
    private String refType;
    @ApiModelProperty(required = true, hidden = true)
    private Long userId;
}
