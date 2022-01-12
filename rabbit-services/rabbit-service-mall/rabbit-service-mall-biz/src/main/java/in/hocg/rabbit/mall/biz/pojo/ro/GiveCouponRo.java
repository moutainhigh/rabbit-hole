package in.hocg.rabbit.mall.biz.pojo.ro;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hocgin on 2020/3/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class GiveCouponRo {
    @NotNull(message = "请选择用户")
    @Size(min = 1, max = 100, message = "请选择用户(1~100)")
    private List<Long> toUserId;
    @NotNull(message = "请指定生效时间")
    private LocalDateTime startAt;
    @NotNull(message = "请指定失效时间")
    private LocalDateTime endAt;

    @ApiModelProperty(hidden = true)
    private Long ownerUserId;
}
