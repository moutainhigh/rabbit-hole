package in.hocg.rabbit.com.biz.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.tree.TreeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
    @ApiModelProperty("倒赞数量")
    @TableField("dislikes_count")
    private Long dislikesCount;

    @ApiModelProperty("评论人")
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    @TableField(value = "last_updater", fill = FieldFill.UPDATE)
    private Long lastUpdater;
    @TableField(value = "last_updated_at", fill = FieldFill.UPDATE)
    private LocalDateTime lastUpdatedAt;
}
