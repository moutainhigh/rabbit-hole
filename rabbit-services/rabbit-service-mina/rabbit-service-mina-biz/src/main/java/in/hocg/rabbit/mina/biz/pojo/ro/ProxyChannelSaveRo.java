package in.hocg.rabbit.mina.biz.pojo.ro;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.validation.autoconfigure.group.Insert;
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
    @NotNull(groups = {Insert.class}, message = "拥有人不能为空")
    @ApiModelProperty("拥有人")
    private Long ownerId;
    @NotNull(groups = {Insert.class}, message = "隧道类型不能为空")
    @ApiModelProperty("隧道类型")
    private String type;
    @NotNull(groups = {Insert.class}, message = "本地端口不能为空")
    @ApiModelProperty("本地端口")
    private Integer localPort;
    @NotNull(groups = {Insert.class}, message = "本地IP不能为空")
    @ApiModelProperty("本地IP")
    private Long localIp;
    @ApiModelProperty("域名前缀")
    private String suffix;
    @ApiModelProperty("开启状态")
    private Boolean enabled;

    @ApiModelProperty(value = "创建者", hidden = true)
    private Long userId;
}
