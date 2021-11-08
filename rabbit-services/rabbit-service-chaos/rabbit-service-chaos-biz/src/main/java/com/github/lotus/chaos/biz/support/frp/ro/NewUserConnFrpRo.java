package com.github.lotus.chaos.biz.support.frp.ro;

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
@ApiModel(description = "创建用户连接 (支持 tcp、stcp、https 和 tcpmux 协议)。")
public class NewUserConnFrpRo {
    private UserComFrpRo user;
    @JsonProperty("proxy_name")
    private String proxyName;
    @JsonProperty("proxy_type")
    private String proxyType;
    @JsonProperty("remote_addr")
    private String remoteAddr;
}
