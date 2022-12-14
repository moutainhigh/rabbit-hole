package in.hocg.rabbit.ums.api.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2020/11/30
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@Setter
@Accessors(chain = true)
public class InsertSocialRo {
    @ApiModelProperty("账号")
    private Long userId;
    @ApiModelProperty("社交类型")
    private String socialType;
    @ApiModelProperty("社交账号")
    private String socialId;
}
