package in.hocg.rabbit.mina.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * [功能模块] 分享内容表
 * </p>
 *
 * @author hocgin
 * @since 2022-03-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mina_share_content")
public class ShareContent extends CommonEntity<ShareContent> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编码")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("任务名称")
    @TableField("title")
    private String title;
    @ApiModelProperty("内容")
    @TableField("content")
    private String content;

}
