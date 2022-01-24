package in.hocg.rabbit.mall.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractEntity;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.LogicDeletedEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * [订单模块] 订单支付记录表
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oms_order_pay_record")
public class OrderPayRecord extends LogicDeletedEntity<OrderPayRecord> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单")
    @TableField("order_id")
    private Long orderId;

}
