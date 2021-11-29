package com.github.lotus.mina.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/11/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class ProxyChannelOrdinaryVo {
    @ApiModelProperty("隧道唯一标识")
    private String channelId;
    @ApiModelProperty("拥有人")
    private Long userId;
    @ApiModelProperty("隧道类型")
    private String type;
    @ApiModelProperty("本地端口")
    private Integer localPort;
    @ApiModelProperty("本地IP")
    private String localIp;
    @ApiModelProperty("域名前缀: hocgin")
    private String prefix;
    @ApiModelProperty("域名后缀: forward.hocgin.top")
    private String suffix;
    @ApiModelProperty("开启状态")
    private Boolean enabled;
}
