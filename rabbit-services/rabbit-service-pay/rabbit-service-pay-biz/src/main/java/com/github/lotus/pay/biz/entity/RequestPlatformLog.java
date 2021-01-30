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
 * [支付网关] 所有和第三方支付交易日志表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_request_platform_log")
public class RequestPlatformLog extends AbstractEntity<RequestPlatformLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("接入平台")
    @TableField("access_platform_id")
    private Long accessPlatformId;
    @ApiModelProperty("应用单号(接入方): [退款单号/交易单号]")
    @TableField("out_request_sn")
    private String outRequestSn;
    @ApiModelProperty("应用单号(网关): [退款单号/交易单号]")
    @TableField("request_sn")
    private String requestSn;
    @ApiModelProperty("交易单号(网关)")
    @TableField("trade_sn")
    private String tradeSn;
    @ApiModelProperty("请求头")
    @TableField("request_header")
    private String requestHeader;
    @ApiModelProperty("请求参数")
    @TableField("request_params")
    private String requestParams;
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
