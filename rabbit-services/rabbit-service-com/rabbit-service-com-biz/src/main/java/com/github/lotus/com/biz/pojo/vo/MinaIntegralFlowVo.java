package com.github.lotus.com.biz.pojo.vo;

import com.github.lotus.chaos.api.ChaosNamedAPI;
import com.github.lotus.chaos.api.NamedType;
import in.hocg.boot.named.annotation.Named;
import in.hocg.boot.named.annotation.UseNamedService;
import in.hocg.boot.web.autoconfiguration.jackson.bigdecimal.BigDecimalFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2021/6/26
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class MinaIntegralFlowVo {
    @ApiModelProperty("触发事件")
    private String eventType;
    @ApiModelProperty("变更类型")
    private String changeType;
    @BigDecimalFormat
    @ApiModelProperty("变更值")
    private BigDecimal changeValue;
    @ApiModelProperty("变更备注")
    private String remark;
    @ApiModelProperty("过期时间")
    private LocalDateTime expireAt;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
}
