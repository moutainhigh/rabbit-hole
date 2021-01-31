package com.github.lotus.pay.biz.entity;

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
 * [支付网关] 事件通知列表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_notify_access_app")
public class NotifyAccessApp extends AbstractEntity<NotifyAccessApp> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("应用单号(网关): [退款单号/交易单号]")
    @TableField("request_sn")
    private String requestSn;
    @ApiModelProperty("交易单号(网关)")
    @TableField("trade_sn")
    private String tradeSn;
    @ApiModelProperty("采用的签名方式: MD5 RSA RSA2 HASH-MAC等")
    @TableField("sign_type")
    private String signType;
    @ApiModelProperty("通知事件类型，pay=>支付事件; refund=>退款事件")
    @TableField("notify_type")
    private String notifyType;
    @ApiModelProperty("通知事件状态: init=>初始化; pending=>进行中; success=>成功; fail=>失败; close=>关闭")
    @TableField("notify_status")
    private String notifyStatus;
    @ApiModelProperty("版本")
    @TableField("version")
    private Integer version;
    @ApiModelProperty("字符编码")
    @TableField("input_charset")
    private String inputCharset;
    @ApiModelProperty("完成通知时间")
    @TableField("finish_at")
    private LocalDateTime finishAt;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("updated_at")
    private LocalDateTime updatedAt;



}
