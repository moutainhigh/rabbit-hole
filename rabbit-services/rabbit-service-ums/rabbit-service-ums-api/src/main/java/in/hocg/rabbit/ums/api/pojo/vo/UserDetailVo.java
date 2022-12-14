package in.hocg.rabbit.ums.api.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "用户详情")
public class UserDetailVo {
    private Long id;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("头像")
    private String avatarUrl;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("过期状态")
    private Boolean expired;
    @ApiModelProperty("锁定状态")
    private Boolean locked;
    @ApiModelProperty("启用状态")
    private Boolean enabled;
}
