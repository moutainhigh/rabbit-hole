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
 * [订单模块] 配送单
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oms_order_delivery")
public class OrderDelivery extends LogicDeletedEntity<OrderDelivery> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单")
    @TableField("order_id")
    private Long orderId;
    @ApiModelProperty("配送状态")
    @TableField("status")
    private String status;
    @ApiModelProperty("编号")
    @TableField("encoding")
    private String encoding;

    @ApiModelProperty("发货人姓名")
    @TableField("delivery_name")
    private String deliveryName;
    @ApiModelProperty("发货人电话")
    @TableField("delivery_tel")
    private String deliveryTel;
    @ApiModelProperty("发货人邮编")
    @TableField("delivery_postcode")
    private String deliveryPostcode;
    @ApiModelProperty("发货人区域编码")
    @TableField("delivery_adcode")
    private String deliveryAdcode;
    @ApiModelProperty("发货人省份/直辖市")
    @TableField("delivery_province")
    private String deliveryProvince;
    @ApiModelProperty("发货人城市")
    @TableField("delivery_city")
    private String deliveryCity;
    @ApiModelProperty("发货人区")
    @TableField("delivery_region")
    private String deliveryRegion;
    @ApiModelProperty("发货人详细地址")
    @TableField("delivery_address")
    private String deliveryAddress;



}
