package com.github.lotus.chaos.biz.support.frp.ro;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * Created by hocgin on 2021/11/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "创建代理的相关信息")
public class NewProxyFrpRo {
    private UserComFrpRo user;
    @JsonProperty("proxy_name")
    private String proxyName;
    @JsonProperty("proxy_type")
    private String proxyType;
    @JsonProperty("use_encryption")
    private Boolean useEncryption;
    @JsonProperty("use_compression")
    private Boolean useCompression;
    private String group;
    @JsonProperty("group_key")
    private String groupKey;

    // tcp and udp only
    @JsonProperty("remote_port")
    private Integer remotePort;

    // http and https only
    @JsonProperty("custom_domains")
    private List<String> customDomains;
    private String subdomain;
    private String locations;
    @JsonProperty("http_user")
    private String httpUser;
    @JsonProperty("http_pwd")
    private String httpPwd;
    @JsonProperty("host_header_rewrite")
    private String hostHeaderRewrite;
    private Headers headers;

    // stcp only
    private String sk;

    // tcpmux only
    private String multiplexer;
    private Metas metas;

    @Data
    public static class Headers {

    }

    @Data
    public static class Metas {

    }
}
