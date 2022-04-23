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
 * [内容模块] 文档对象表
 * </p>
 *
 * @author hocgin
 * @since 2022-02-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("rcm_doc")
public class Doc extends CommonEntity<Doc> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("引用类型")
    @TableField("ref_type")
    private String refType;
    @ApiModelProperty("引用对象")
    @TableField("ref_id")
    private Long refId;
    @ApiModelProperty("所属用户")
    @TableField("owner_user_id")
    private Long ownerUserId;
    @ApiModelProperty("浏览次数")
    @TableField("view_count")
    private Integer viewCount;
    @ApiModelProperty("喜欢次数")
    @TableField("like_count")
    private Integer likeCount;
}
