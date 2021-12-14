package in.hocg.rabbit.com.biz.pojo.vo;

import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.rabbit.common.datadict.com.integralflow.ChangeType;
import in.hocg.rabbit.common.datadict.com.integralflow.EventType;
import in.hocg.boot.named.annotation.InjectNamed;

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
@InjectNamed
public class MinaIntegralFlowVo {
    @ApiModelProperty("触发事件")
    private String eventType;
    @ChaosNamed(idFor = "eventType", type = ChaosNamedType.DataDictName, args = {EventType.KEY})
    private String eventTypeName;
    @ApiModelProperty("变更类型")
    private String changeType;
    @ChaosNamed(idFor = "changeType", type = ChaosNamedType.DataDictName, args = {ChangeType.KEY})
    private String changeTypeName;
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
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2Nickname)
    private String creatorName;
}
