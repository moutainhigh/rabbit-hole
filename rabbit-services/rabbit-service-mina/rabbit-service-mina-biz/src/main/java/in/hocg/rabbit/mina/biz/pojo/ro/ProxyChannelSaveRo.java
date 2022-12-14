package in.hocg.rabbit.mina.biz.pojo.ro;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.validation.group.Insert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2021/11/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "代理隧道")
public class ProxyChannelSaveRo {
    @ApiModelProperty("隧道ID")
    private String channelId;
    @NotNull(groups = {Insert.class}, message = "拥有人不能为空")
    @ApiModelProperty("拥有人")
    private Long userId;
    @NotNull(groups = {Insert.class}, message = "隧道类型不能为空")
    @ApiModelProperty("隧道类型")
    private String type;
    @NotNull(groups = {Insert.class}, message = "本地端口不能为空")
    @ApiModelProperty("本地端口")
    private Integer localPort;
    @NotNull(groups = {Insert.class}, message = "本地IP不能为空")
    @ApiModelProperty("本地IP")
    private String localIp;
    @ApiModelProperty("域名前缀")
    private String prefix;
    @ApiModelProperty("域名后缀")
    private String suffix;
    @ApiModelProperty("开启状态")
    private Boolean enabled;

}
