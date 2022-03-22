package in.hocg.rabbit.com.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [通用模块] 用户的评论行为表
 * </p>
 *
 * @author hocgin
 * @since 2021-09-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_comment_user_action")
public class CommentUserAction extends CommonEntity<CommentUserAction> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论")
    @TableField("comment_id")
    private Long commentId;
    @ApiModelProperty("用户")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("行为")
    @TableField("action")
    private String action;

}
