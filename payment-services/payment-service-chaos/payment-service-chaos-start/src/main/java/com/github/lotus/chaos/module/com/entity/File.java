package com.github.lotus.chaos.module.com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
import in.hocg.boot.mybatis.plus.autoconfiguration.constant.DataDictEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * [基础模块] 文件引用表
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_file")
public class File extends AbstractEntity<File> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("文件名")
    @TableField("filename")
    private String filename;
    @ApiModelProperty("链接地址")
    @TableField("file_url")
    private String fileUrl;
    @ApiModelProperty("业务ID")
    @TableField("rel_id")
    private Long relId;
    @ApiModelProperty("业务类型")
    @TableField("rel_type")
    private String relType;
    @ApiModelProperty("排序,默认:1000")
    @TableField("sort")
    private Integer sort;
    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    @TableField("creator")
    private Long creator;

}
