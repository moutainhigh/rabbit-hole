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
 * [通用] 用户积分表
 * </p>
 *
 * @author hocgin
 * @since 2021-06-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("com_user_integral")
public class UserIntegral extends CommonEntity<UserIntegral> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户")
    @TableField("user_id")
    private Long userId;
    @ApiModelProperty("可用积分")
    @TableField("avail_integral")
    private BigDecimal availIntegral;
    @ApiModelProperty("已用积分")
    @TableField("used_integral")
    private BigDecimal usedIntegral;


}
