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
 * [支付网关] 所有第三方支付通知日志表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_platform_notify_log")
public class PlatformNotifyLog extends AbstractEntity<PlatformNotifyLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("接入平台")
    @TableField("access_platform_id")
    private Long accessPlatformId;
    @ApiModelProperty("功能: pay=>支付; refund=>退款")
    @TableField("feature")
    private String feature;
    @ApiModelProperty("请求体")
    @TableField("request_body")
    private String requestBody;
    @ApiModelProperty("响应内容")
    @TableField("return_body")
    private String returnBody;
    @ApiModelProperty("日志类型: pay=>支付; refund=>退款; async_notify=>异步通知; sync_notify=>同步通知; query=>查询")
    @TableField("log_type")
    private String logType;
    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建ip")
    @TableField("created_ip")
    private String createdIp;



}
