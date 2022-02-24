package in.hocg.rabbit.rcm.api.pojo.ro;

import in.hocg.boot.validation.annotation.EnumRange;
import in.hocg.rabbit.common.datadict.common.RefType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2022/2/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class CreateDocRo {
    @NotNull(message = "引用类型不能为空")
    @ApiModelProperty("引用类型")
    @EnumRange(enumClass = RefType.class, message = "引用类型不合法")
    private String refType;
    @NotNull(message = "引用ID不能为空")
    @ApiModelProperty("引用ID")
    private Long refId;

    @ApiModelProperty("所属用户")
    @NotNull(message = "所属用户不能为空")
    private Long ownerUserId;
}
