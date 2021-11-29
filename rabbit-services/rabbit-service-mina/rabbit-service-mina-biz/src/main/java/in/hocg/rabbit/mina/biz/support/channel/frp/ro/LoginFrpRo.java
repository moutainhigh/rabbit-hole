package in.hocg.rabbit.mina.biz.support.channel.frp.ro;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by hocgin on 2021/11/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "用户登录操作信息")
public class LoginFrpRo extends UserComFrpRo {
    private String version;
    private String hostname;
    private String os;
    private String arch;
    private Long timestamp;
    @JsonProperty("privilege_key")
    private String privilegeKey;
    @JsonProperty("pool_count")
    private Long poolCount;
}
