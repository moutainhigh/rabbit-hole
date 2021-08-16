package com.github.lotus.bmw.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [支付模块] 支付宝配置表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_payment_mch_config_alipay")
public class PaymentMchConfigAlipay extends AbstractEntity<PaymentMchConfigAlipay> {

    private static final long serialVersionUID = 1L;

    /**
     * ID 等同于
     *
     * @see PaymentMch.id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    @ApiModelProperty("appid")
    @TableField("appid")
    private String appid;
    @ApiModelProperty("public key")
    @TableField("public_key")
    private String publicKey;
    @ApiModelProperty("private key")
    @TableField("private_key")
    private String privateKey;
    @ApiModelProperty("是否测试模式")
    @TableField("is_dev")
    private Boolean isDev;


}
