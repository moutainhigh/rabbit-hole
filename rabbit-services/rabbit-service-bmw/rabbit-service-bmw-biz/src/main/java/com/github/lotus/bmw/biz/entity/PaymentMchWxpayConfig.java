package com.github.lotus.bmw.biz.entity;

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
 * [支付模块] 微信支付配置表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_payment_mch_wxpay_config")
public class PaymentMchWxpayConfig extends AbstractEntity<PaymentMchWxpayConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * ID 等同于
     * @see PaymentMch.id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    @ApiModelProperty("appid")
    @TableField("appid")
    private String appid;
    @ApiModelProperty("mch_id")
    @TableField("mch_id")
    private String mchId;
    @ApiModelProperty("key")
    @TableField("key_str")
    private String keyStr;
    @ApiModelProperty("cert file text")
    @TableField("cert_str")
    private String certStr;


}
