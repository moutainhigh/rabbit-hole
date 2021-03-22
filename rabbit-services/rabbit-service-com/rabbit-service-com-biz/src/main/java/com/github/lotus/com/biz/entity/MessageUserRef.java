package com.github.lotus.com.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractEntity;
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
public class MessageUserRef extends AbstractEntity<MessageUserRef> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("接收人")
    @TableField("receiver_user")
    private Long receiverUser;
    @ApiModelProperty("消息类型")
    @TableField("ref_type")
    private String refType;
    @ApiModelProperty("消息对象")
    @TableField("ref_id")
    private Long refId;
    @ApiModelProperty("读取时间")
    @TableField("read_at")
    private LocalDateTime readAt;
    @ApiModelProperty("创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

}
