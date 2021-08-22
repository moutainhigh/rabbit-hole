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
 * [支付模块] 通知接入商户任务表
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bmw_sync_access_mch_task")
public class SyncAccessMchTask extends AbstractEntity<SyncAccessMchTask> {

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
    @ApiModelProperty("任务类型")
    @TableField("task_type")
    private String taskType;
    @ApiModelProperty("通知轮次")
    @TableField("notify_count")
    private Integer notifyCount;
    @ApiModelProperty("计划通知时间")
    @TableField("plan_notify_at")
    private LocalDateTime planNotifyAt;
    @ApiModelProperty("通知地址")
    @TableField("notify_url")
    private String notifyUrl;

    @ApiModelProperty("通知内容")
    @TableField("notify_body")
    private String notifyBody;
    @ApiModelProperty("响应内容")
    @TableField("return_body")
    private String returnBody;
    @ApiModelProperty("完成通知时间")
    @TableField("finished_at")
    private LocalDateTime finishedAt;
    /**
     * @see com.github.lotus.common.datadict.common.HandleStatus
     */
    @ApiModelProperty("通知状态")
    @TableField("status")
    private String status;
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
