package com.github.lotus.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.lotus.chaos.api.NamedType;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@InjectNamed
public class UserCouponComplexVo {
    @ApiModelProperty("用户优惠券ID")
    private Long id;
    @ApiModelProperty("优惠券编号")
    private String couponNo;
    private Long couponId;
    @ApiModelProperty("使用状态")
    private String status;
    @ApiModelProperty("拥有人")
    private Long userId;
    @ApiModelProperty("优惠券生效时间")
    private LocalDateTime startAt;
    @ApiModelProperty("优惠券失效时间")
    private LocalDateTime endAt;
    @ApiModelProperty("优惠券使用时间")
    private LocalDateTime usedAt;
    @ApiModelProperty("领取时间")
    private LocalDateTime createdAt;

    @ApiModelProperty("折扣方式")
    private String couponType;
    @ApiModelProperty("优惠券标题")
    private String title;
    @ApiModelProperty("优惠券使用说明")
    private String useInstructions;
    @ApiModelProperty("使用方式")
    private String useStint;
    @ApiModelProperty("可用平台")
    private String usePlatform;
    @ApiModelProperty("满减金额/折扣率")
    private BigDecimal credit;
    @ApiModelProperty("最低启用金额")
    private BigDecimal minPoint;
    @ApiModelProperty("优惠上限金额")
    private BigDecimal ceiling;

    @ApiModelProperty("可用商品品类")
    private List<ProductCategoryComplexVo> canUseProductCategory = Collections.emptyList();
    @ApiModelProperty("可用商品")
    private List<ProductComplexVo> canUseProduct = Collections.emptyList();
}
