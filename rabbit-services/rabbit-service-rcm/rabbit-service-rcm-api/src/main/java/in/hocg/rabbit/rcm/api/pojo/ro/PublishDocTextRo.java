package in.hocg.rabbit.rcm.api.pojo.ro;

import in.hocg.boot.validation.annotation.EnumRange;
import in.hocg.rabbit.common.datadict.common.RefType;
import in.hocg.rabbit.rcm.api.enums.DocType;
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
    @NotNull(message = "文档对象不能为空")
    @ApiModelProperty("文档对象")
    private Long docId;
    @NotNull(message = "文本类型不能为空")
    @EnumRange(enumClass = DocType.class, message = "文本类型不合法")
    @ApiModelProperty("文本类型")
    private String doctype;
    @ApiModelProperty("文本")
    private String content;
    @NotNull(message = "发布状态不能为空")
    @ApiModelProperty("发布状态")
    private Boolean published = true;
}
