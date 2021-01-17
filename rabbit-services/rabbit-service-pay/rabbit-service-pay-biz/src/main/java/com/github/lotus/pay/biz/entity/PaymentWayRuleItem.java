package com.github.lotus.pay.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * [支付网关] 支付渠道规则对应的支付渠道表
 * </p>
 *
 * @author hocgin
 * @since 2020-07-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_payment_way_rule_item")
public class PaymentWayRuleItem extends AbstractEntity<PaymentWayRuleItem> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("支付渠道规则")
    @TableField("rule_id")
    private Long ruleId;
    @ApiModelProperty("支付渠道名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("支付渠道码")
    @TableField("payment_way_code")
    private String paymentWayCode;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private String enabled;
    @ApiModelProperty("排序, 从大到小降序")
    @TableField("sort")
    private Integer sort;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("updated_at")
    private LocalDateTime updatedAt;


}
