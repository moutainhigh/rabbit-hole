package in.hocg.rabbit.com.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.ScrollRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class UserAddressClientScrollRo extends ScrollRo {
    @ApiModelProperty(value = "所属用户", hidden = true)
    private Long ownerUserId;
}
