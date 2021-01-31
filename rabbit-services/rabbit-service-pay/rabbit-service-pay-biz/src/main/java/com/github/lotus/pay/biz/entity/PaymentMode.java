package com.github.lotus.pay.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * [支付网关] 第三方支付平台对应的支付方式表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_payment_mode")
public class PaymentMode extends AbstractEntity<PaymentMode> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("第三方支付平台")
    @TableField("payment_platform_id")
    private Long paymentPlatformId;
    @ApiModelProperty("编码")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("标题")
    @TableField("title")
    private String title;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Integer enabled;



}
