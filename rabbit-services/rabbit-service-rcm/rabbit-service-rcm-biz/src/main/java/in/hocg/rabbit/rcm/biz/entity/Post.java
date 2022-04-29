package in.hocg.rabbit.rcm.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.LogicDeletedEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * [内容模块] 帖文表
 * </p>
 *
 * @author hocgin
 * @since 2022-04-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("rcm_post")
public class Post extends LogicDeletedEntity<Post> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;
    @ApiModelProperty("简介")
    @TableField("summary")
    private String summary;
    @ApiModelProperty("内容")
    @TableField("doc_id")
    private Long docId;
    @ApiModelProperty("标签")
    @TableField("tags")
    private String tags;
    @ApiModelProperty("类目")
    @TableField("category_id")
    private Long categoryId;
    @ApiModelProperty("展览图")
    @TableField("thumbnail_url")
    private String thumbnailUrl;
    @ApiModelProperty("原文链接")
    @TableField("original_link")
    private String originalLink;
    @ApiModelProperty("草稿状态")
    @TableField("drafted")
    private Boolean drafted;
    @ApiModelProperty("启用状态")
    @TableField("enabled")
    private Boolean enabled;


}
