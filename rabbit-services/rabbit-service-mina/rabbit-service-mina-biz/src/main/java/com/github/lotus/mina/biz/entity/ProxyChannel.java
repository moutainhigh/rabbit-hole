package com.github.lotus.mina.biz.entity;

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
 * [代理] 用户隧道表
 * </p>
 *
 * @author hocgin
 * @since 2021-11-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("dl_proxy_channel")
public class ProxyChannel extends AbstractEntity<ProxyChannel> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("隧道唯一标识")
    @TableField("channel_id")
    private String channelId;
    @ApiModelProperty("拥有人")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("隧道类型")
    @TableField("type")
    private String type;
    @ApiModelProperty("本地端口")
    @TableField("local_port")
    private Integer localPort;
    @ApiModelProperty("本地IP")
    @TableField("local_ip")
    private Long localIp;
    @ApiModelProperty("自定义域名")
    @TableField("custom_domains")
    private String customDomains;
    @ApiModelProperty("开启状态")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("租户")
    @TableField("tenant_id")
    private Long tenantId;
    @ApiModelProperty("删除状态(未删除 != 0)")
    @TableField("delete_flag")
    private Long deleteFlag;
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
