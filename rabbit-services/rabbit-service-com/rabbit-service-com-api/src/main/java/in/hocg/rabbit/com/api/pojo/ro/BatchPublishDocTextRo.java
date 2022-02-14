package in.hocg.rabbit.com.api.pojo.ro;

import in.hocg.boot.validation.annotation.EnumRange;
import in.hocg.rabbit.com.api.enums.DocType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by hocgin on 2022/2/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class BatchPublishDocTextRo implements Serializable {
    @NotNull(message = "引用类型不能为空")
    @ApiModelProperty("引用类型")
    private String refType;
    @NotNull(message = "引用ID不能为空")
    @ApiModelProperty("引用ID")
    private Long refId;
    @Valid
    @ApiModelProperty("文本")
    private List<TextDto> texts;

    @Data
    @Accessors(chain = true)
    public static class TextDto implements Serializable {
        @ApiModelProperty("排序(可选)")
        private Integer priority;
        @ApiModelProperty("文本")
        private String text;
        @NotNull(message = "文本类型不能为空")
        @EnumRange(enumClass = DocType.class, message = "文本类型不合法")
        @ApiModelProperty("文本类型")
        private String doctype;
    }
}
