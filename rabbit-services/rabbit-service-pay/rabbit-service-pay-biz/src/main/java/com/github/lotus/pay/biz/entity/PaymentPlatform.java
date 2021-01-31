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
 * [支付网关] 第三方支付平台表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_payment_platform")
public class PaymentPlatform extends AbstractEntity<PaymentPlatform> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
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
