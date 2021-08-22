package com.github.lotus.mall.biz.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.lotus.chaos.api.NamedType;
import com.google.common.collect.Lists;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@InjectNamed
public class CouponComplexVo {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("优惠券名称")
    private String title;
    @ApiModelProperty("折扣方式")
    private String couponType;
    @ApiModelProperty("可用类型")
    private String useStint;
    @ApiModelProperty("可用平台")
    private String usePlatform;

    @ApiModelProperty("优惠券使用说明")
    private String useInstructions;
    @ApiModelProperty("订单最低启用金额")
    private BigDecimal minPoint;
    @ApiModelProperty("满减金额/折扣率")
    private BigDecimal credit;
    @ApiModelProperty("优惠上限")
    private BigDecimal ceiling;

    @ApiModelProperty("可用商品")
    private List<ProductComplexVo> canUseProduct;
    @ApiModelProperty("可用商品品类")
    private List<ProductCategoryComplexVo> canUseProductCategory;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    private Long creator;
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("最后更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("最后更新时间")
    private Long lastUpdater;
    @Named(idFor = "lastUpdater", type = NamedType.Userid2Nickname)
    private String lastUpdaterName;

}
