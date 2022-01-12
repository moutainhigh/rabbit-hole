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
 * [订单模块] 订单详情(商品)表
 * </p>
 *
 * @author hocgin
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oms_order_item_sku")
public class OrderItemSku extends LogicDeletedEntity<OrderItemSku> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单详情ID")
    @TableField("order_item_id")
    private Long orderItemId;
    @ApiModelProperty("商品主图")
    @TableField("image_url")
    private String imageUrl;
    @ApiModelProperty("商品SKU ID")
    @TableField("sku_id")
    private Long skuId;
    @ApiModelProperty("商品SKU条码")
    @TableField("sku_code")
    private String skuCode;
    @ApiModelProperty("商品规格")
    @TableField("sku_spec_data")
    private String skuSpecData;


}
