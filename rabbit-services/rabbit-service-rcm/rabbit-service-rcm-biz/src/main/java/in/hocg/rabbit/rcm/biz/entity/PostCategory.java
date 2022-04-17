package in.hocg.rabbit.rcm.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.LogicDeletedEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [内容模块] 帖文类目表
 * </p>
 *
 * @author hocgin
 * @since 2022-04-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("rcm_post_category")
public class PostCategory extends LogicDeletedEntity<PostCategory> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标题")
    @TableField("encoding")
    private String encoding;
    @ApiModelProperty("标题")
    @TableField("title")
    private String title;



}
