package com.github.lotus.com.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import in.hocg.boot.mybatis.plus.autoconfiguration.tree.TreeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * [通用模块] 评论表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_comment")
public class Comment extends TreeEntity<Comment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论对象ID")
    @TableField("target_id")
    private Long targetId;
    @ApiModelProperty("评论内容")
    @TableField("content")
    private String content;
    @ApiModelProperty("点赞数量")
    @TableField("likes_count")
    private Long likesCount;

    @ApiModelProperty("评论人")
    @TableField("creator")
    private Long creator;
    @TableField("created_at")
    private LocalDateTime createdAt;
    @TableField("last_updater")
    private Long lastUpdater;
    @TableField("last_updated_at")
    private LocalDateTime lastUpdatedAt;
}
