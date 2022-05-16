package in.hocg.rabbit.com.biz.pojo.ro.comment;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.ScrollRo;
import in.hocg.boot.validation.annotation.StringRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/2/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel
public class CommentClientScrollRo extends ScrollRo {

    @ApiModelProperty("排序")
    private Boolean orderDesc = true;
    @ApiModelProperty("显示类型")
    @StringRange(strings = {"list", "kanban"})
    private String showType = ShowType.list.name();

    @ApiModelProperty(value = "评论对象", hidden = true)
    private Long targetId;
    @ApiModelProperty(value = "对象", hidden = true, required = true)
    private Long refId;
    @ApiModelProperty(value = "对象类型", hidden = true, required = true)
    private String refType;
    @ApiModelProperty(required = true, hidden = true)
    private Long userId;


    public enum ShowType {
        list, kanban
    }
}
