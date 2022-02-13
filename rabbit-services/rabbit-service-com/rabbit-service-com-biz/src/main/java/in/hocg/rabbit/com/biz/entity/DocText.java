package in.hocg.rabbit.com.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;

import java.io.Serializable;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.LogicDeletedEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * [内容模块] 富文本内容表
 * </p>
 *
 * @author hocgin
 * @since 2022-02-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_doc_text")
public class DocText extends LogicDeletedEntity<DocText> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("对象类型")
    @TableField("ref_type")
    private String refType;
    @ApiModelProperty("对象")
    @TableField("ref_id")
    private Long refId;
    @ApiModelProperty("文本类型: text=>普通文本;rich=>富文本;markdown=>.md")
    @TableField("doctype")
    private String doctype;
    @ApiModelProperty("文本")
    @TableField("text")
    private String text;
    @ApiModelProperty("关键词(;分割)")
    @TableField("keyword")
    private String keyword;
    @ApiModelProperty("排序")
    @TableField("priority")
    private Integer priority;

    @ApiModelProperty("租户")
    @TableField("tenant_id")
    private Long tenantId;

}
