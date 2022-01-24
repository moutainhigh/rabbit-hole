package in.hocg.rabbit.com.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [消息模块] 用户订阅配置表
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mms_notice_user_config")
public class NoticeUserConfig extends AbstractEntity<NoticeUserConfig> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("事件类型")
    @TableField("event_type")
    private String eventType;
    @ApiModelProperty("订阅对象类型")
    @TableField("ref_type")
    private String refType;
    @ApiModelProperty("订阅对象")
    @TableField("ref_id")
    private Long refId;
    @ApiModelProperty("订阅人")
    @TableField("subscriber_user")
    private Long subscriberUser;
    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    @TableField("creator")
    private Long creator;



}
