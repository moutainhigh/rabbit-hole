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

/**
 * <p>
 * [支付网关] 微信支付配置表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_platform_wxpay_config")
public class PlatformWxpayConfig extends AbstractEntity<PlatformWxpayConfig> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
