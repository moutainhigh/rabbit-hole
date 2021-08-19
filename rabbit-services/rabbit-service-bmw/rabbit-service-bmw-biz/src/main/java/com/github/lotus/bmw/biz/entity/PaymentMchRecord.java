package com.github.lotus.bmw.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * [支付模块] 支付商户业务记录表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_payment_mch_record")
public class PaymentMchRecord extends AbstractEntity<PaymentMchRecord> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("业务类型")
    @TableField("ref_type")
    private String refType;
    @ApiModelProperty("业务对象")
    @TableField("ref_id")
    private Long refId;
    @ApiModelProperty("行为类型")
    @TableField("biz_type")
    private String bizType;
    @ApiModelProperty("支付商户")
    @TableField("payment_mch_id")
    private Long paymentMchId;
    @ApiModelProperty("附加参数")
    @TableField("attach")
    private String attach;
    @ApiModelProperty("请求日志记录")
    @TableField("log_id")
    private Long logId;
    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    @TableField("creator")
    private Long creator;
    @ApiModelProperty("更新时间")
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    @TableField("last_updater")
    private Long lastUpdater;



}
