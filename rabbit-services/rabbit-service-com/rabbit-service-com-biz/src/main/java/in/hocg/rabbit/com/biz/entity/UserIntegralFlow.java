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

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * [通用] 用户积分流水表
 * </p>
 *
 * @author hocgin
 * @since 2021-06-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_user_integral_flow")
public class UserIntegralFlow extends CommonEntity<UserIntegralFlow> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("触发事件")
    @TableField("event_type")
    private String eventType;
    @ApiModelProperty("变更类型")
    @TableField("change_type")
    private String changeType;
    @ApiModelProperty("变更值")
    @TableField("change_value")
    private BigDecimal changeValue;
    @ApiModelProperty("变更备注")
    @TableField("remark")
    private String remark;
    @ApiModelProperty("过期时间")
    @TableField("expire_at")
    private LocalDateTime expireAt;


}
