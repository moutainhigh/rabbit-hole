package in.hocg.rabbit.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import in.hocg.rabbit.mall.api.named.MallNamed;
import in.hocg.rabbit.mall.api.named.MallNamedType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/3/17.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@InjectNamed
@Accessors(chain = true)
public class UserCouponComplexVo {
    @ApiModelProperty("用户优惠券ID")
    private Long id;
    @ApiModelProperty("优惠券编号")
    private String encoding;
    @ApiModelProperty("优惠券")
    private Long couponId;
    @ApiModelProperty("使用状态")
    private String status;
    @MallNamed(idFor = "status", type = MallNamedType.DataDictName, args = {MallDataDictKeys.USER_COUPON_STATUS})
    private String statusName;

    @ApiModelProperty("拥有人")
    private Long ownerUserId;
    @ChaosNamed(idFor = "ownerUserId", type = ChaosNamedType.Userid2Nickname)
    private String ownerUserName;

    @ApiModelProperty("优惠信息")
    private CouponComplexVo coupon;

    @ApiModelProperty("过期状态")
    private Boolean expiredFlag;
    @ApiModelProperty("优惠券生效时间")
    private LocalDateTime startAt;
    @ApiModelProperty("优惠券失效时间")
    private LocalDateTime endAt;

    @ApiModelProperty("使用时间")
    private LocalDateTime usedAt;
    @ApiModelProperty("实际抵扣金额")
    private BigDecimal usedAmt;


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
