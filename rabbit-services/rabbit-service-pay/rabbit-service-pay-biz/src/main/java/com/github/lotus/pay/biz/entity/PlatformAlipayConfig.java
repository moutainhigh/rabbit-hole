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
 * [支付网关] 支付宝配置表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_platform_alipay_config")
public class PlatformAlipayConfig extends AbstractEntity<PlatformAlipayConfig> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
