package com.github.lotus.com.biz.entity;

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
 * [通用模块] 评论对象表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_comment_target")
public class CommentTarget extends AbstractEntity<CommentTarget> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("评论对象ID")
    @TableField("rel_id")
    private Long relId;
    @ApiModelProperty("评论对象类型")
    @TableField("rel_type")
    private String relType;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private String enabled;

}
