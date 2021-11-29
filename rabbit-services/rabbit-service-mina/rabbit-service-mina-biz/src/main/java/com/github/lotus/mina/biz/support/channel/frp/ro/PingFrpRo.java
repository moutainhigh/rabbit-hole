package com.github.lotus.mina.biz.support.channel.frp.ro;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/11/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel(description = "心跳相关信息")
public class PingFrpRo implements Serializable {
    private UserComFrpRo user;
    private Long timestamp;
    @JsonProperty("privilege_key")
    private String privilegeKey;
}
