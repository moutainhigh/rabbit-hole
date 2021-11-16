package com.github.lotus.mina.biz.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class ProxyChannelInfoVo {
    private String md5;
    private ProxyConfig config;
    private String cnvStr = "" +
        "[common]\n" +
        "server_addr = frps.hocgin.top\n" +
        "server_port = 30902\n" +
        "\n" +
        "[ams-pay]\n" +
        "type = http\n" +
        "custom_domains = forward.hocgin.top\n" +
        "local_ip = 127.0.0.1\n" +
        "local_port = 21000";

    @Data
    @Accessors(chain = true)
    public static class ProxyConfig {
        private String serverAddr;
        private Integer serverPort;
        @ApiModelProperty("隧道代理信息")
        private List<Proxy> proxies = Collections.emptyList();

        @Data
        @Accessors(chain = true)
        public static class Proxy {
            private String name;

            private String type;
            private String localIp;
            private Integer localPort;
            private String customDomains;
        }
    }
}
