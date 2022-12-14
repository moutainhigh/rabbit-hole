package in.hocg.rabbit.docking.biz.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class WxMaLoginVo {
    @ApiModelProperty("账号")
    private UserDetailVo userDetail;
    @ApiModelProperty("会话标记")
    private String token;

    @Data
    @Accessors(chain = true)
    public static class UserDetailVo {
        @ApiModelProperty("账号ID")
        private Long id;
        @ApiModelProperty("账号")
        private String username;
        @ApiModelProperty("昵称")
        private String nickname;
        @ApiModelProperty("头像")
        private String avatar;
    }
}
