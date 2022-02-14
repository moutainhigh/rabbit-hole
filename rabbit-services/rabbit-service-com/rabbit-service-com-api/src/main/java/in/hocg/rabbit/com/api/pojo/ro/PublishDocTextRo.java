package in.hocg.rabbit.com.api.pojo.ro;

import in.hocg.boot.validation.annotation.EnumRange;
import in.hocg.rabbit.com.api.enums.DocType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2022/2/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class PublishDocTextRo {
    @NotBlank(message = "引用类型不能为空")
    @ApiModelProperty("引用类型")
    private String refType;
    @NotNull(message = "引用ID不能为空")
    @ApiModelProperty("引用ID")
    private Long refId;
    @NotNull(message = "文本类型不能为空")
    @EnumRange(enumClass = DocType.class, message = "文本类型不合法")
    @ApiModelProperty("文本类型")
    private String doctype;
    @ApiModelProperty("文本")
    private String text;
}
