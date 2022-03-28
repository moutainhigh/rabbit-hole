package in.hocg.rabbit.com.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * [消息模块] 用户接收的消息表
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mms_message_user_ref")
public class MessageUserRef extends CommonEntity<MessageUserRef> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("接收人")
    @TableField("receiver_user")
    private Long receiverUser;
    @ApiModelProperty("消息类型")
    @TableField("message_type")
    private String messageType;
    @ApiModelProperty("消息对象")
    @TableField("message_id")
    private Long messageId;
    @ApiModelProperty("读取时间")
    @TableField("read_at")
    private LocalDateTime readAt;

}
