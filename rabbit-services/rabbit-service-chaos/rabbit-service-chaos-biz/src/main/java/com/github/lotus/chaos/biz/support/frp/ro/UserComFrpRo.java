package com.github.lotus.chaos.biz.support.frp.ro;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/11/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class UserComFrpRo {
    private String user;
    private Metas metas;
    @JsonProperty("run_id")
    private String runId;

    @Data
    public static class Metas {
    }
}
