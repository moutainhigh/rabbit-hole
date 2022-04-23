package in.hocg.rabbit.rcm.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;

import java.io.Serializable;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * [内容模块] 文档内容表
 * </p>
 *
 * @author hocgin
 * @since 2022-02-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("rcm_doc_content")
public class DocContent extends CommonEntity<DocContent> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文档对象")
    @TableField("doc_id")
    private Long docId;
    @ApiModelProperty("作废标记")
    @TableField("drop_flag")
    private LocalDateTime dropFlag;
    @ApiModelProperty("文本类型")
    @TableField("doctype")
    private String doctype;
    @ApiModelProperty("内容")
    @TableField("content")
    private String content;
    @ApiModelProperty("概述")
    @TableField("summary")
    private String summary;
    @ApiModelProperty("标题")
    @TableField("title")
    private String title;
    @ApiModelProperty("关键词")
    @TableField("keyword")
    private String keyword;
}
