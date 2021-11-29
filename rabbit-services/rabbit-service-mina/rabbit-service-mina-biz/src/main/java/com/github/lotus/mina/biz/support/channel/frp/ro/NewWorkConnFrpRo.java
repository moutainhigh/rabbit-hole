package com.github.lotus.mina.biz.support.channel.frp.ro;

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
@ApiModel(description = "创建工作连接")
public class NewWorkConnFrpRo {
    private UserComFrpRo user;
    private Long timestamp;
    @JsonProperty("run_id")
    private String runId;
    @JsonProperty("privilege_key")
    private String privilegeKey;
}
