package in.hocg.rabbit.rcm.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [内容模块] 文档内容版本表
 * </p>
 *
 * @author hocgin
 * @since 2022-02-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("rcm_doc_version")
public class DocVersion extends CommonEntity<DocVersion> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("版本名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("文档内容对象")
    @TableField("doc_content_id")
    private Long docContentId;

}
