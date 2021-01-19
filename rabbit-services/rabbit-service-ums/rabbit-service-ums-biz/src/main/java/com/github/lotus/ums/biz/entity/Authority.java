package com.github.lotus.ums.biz.entity;

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
 * [权限模块] 权限表
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ams_authority")
public class Authority extends AbstractEntity<Authority> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("权限名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("权限授权码")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("描述")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("项目ID")
    @TableField("project_id")
    private Long projectId;
    @ApiModelProperty("优先级, 越大优先级越低")
    @TableField("priority")
    private Integer priority;
    @ApiModelProperty("父级ID, 顶级为 NULL")
    @TableField("parent_id")
    private Long parentId;
    @ApiModelProperty("树路径，组成方式: /父路径/当前ID")
    @TableField("tree_path")
    private String treePath;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private String enabled;
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
