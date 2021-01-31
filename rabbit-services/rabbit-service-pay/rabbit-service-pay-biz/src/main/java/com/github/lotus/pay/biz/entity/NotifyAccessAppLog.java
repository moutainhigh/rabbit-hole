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
 * [支付网关] 所有通知应用方日志表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_notify_access_app_log")
public class NotifyAccessAppLog extends AbstractEntity<NotifyAccessAppLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("通知编号ID")
    @TableField("notify_access_app_id")
    private Long notifyAccessAppId;
    @ApiModelProperty("通知调用结果: init=>初始化; success=>响应成功; fail=>响应失败; timeout=>超时失败")
    @TableField("notify_result")
    private String notifyResult;
    @ApiModelProperty("通知内容")
    @TableField("notify_body")
    private String notifyBody;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("updated_at")
    private LocalDateTime updatedAt;



}
