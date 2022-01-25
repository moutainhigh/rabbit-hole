package in.hocg.rabbit.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import in.hocg.rabbit.mall.api.named.MallNamed;
import in.hocg.rabbit.mall.api.named.MallNamedType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hocgin on 2020/3/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
@InjectNamed
public class CouponComplexVo {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("优惠券名称")
    private String title;
    @ApiModelProperty("折扣方式")
    private String type;
    @MallNamed(idFor = "type", type = MallNamedType.DataDictName, args = {MallDataDictKeys.COUPON_TYPE})
    private String typeName;

    @ApiModelProperty("优惠券使用说明")
    private String useInstructions;
    @ApiModelProperty("满减金额/折扣率")
    private BigDecimal credit;
    @ApiModelProperty("优惠上限")
    private BigDecimal ceilingAmt;

    @ApiModelProperty("限制规则")
    private List<StintRuleComplexVo> stintRules;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    private Long creator;
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("最后更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("最后更新时间")
    private Long lastUpdater;
    @ChaosNamed(idFor = "lastUpdater", type = ChaosNamedType.Userid2Nickname)
    private String lastUpdaterName;

}
