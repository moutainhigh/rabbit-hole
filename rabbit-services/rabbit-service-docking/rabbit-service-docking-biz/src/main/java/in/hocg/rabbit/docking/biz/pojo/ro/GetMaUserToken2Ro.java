package in.hocg.rabbit.docking.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by hocgin on 2021/3/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class GetMaUserToken2Ro {
    @NotBlank(message = "参数错误")
    private String code;
    @ApiModelProperty("头像")
    private String avatarUrl;
    @ApiModelProperty("昵称")
    private String nickName;
}
