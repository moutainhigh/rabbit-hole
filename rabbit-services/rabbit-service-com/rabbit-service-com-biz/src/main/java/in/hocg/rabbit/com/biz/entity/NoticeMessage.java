package in.hocg.rabbit.com.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * [消息模块] 订阅消息表
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mms_notice_message")
public class NoticeMessage extends CommonEntity<NoticeMessage> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("事件类型")
    @TableField("event_type")
    private String eventType;
    @ApiModelProperty("订阅对象类型")
    @TableField("ref_type")
    private String refType;
    @ApiModelProperty("订阅对象")
    @TableField("ref_id")
    private Long refId;
    @ApiModelProperty("内容")
    @TableField("content")
    private String content;

}
