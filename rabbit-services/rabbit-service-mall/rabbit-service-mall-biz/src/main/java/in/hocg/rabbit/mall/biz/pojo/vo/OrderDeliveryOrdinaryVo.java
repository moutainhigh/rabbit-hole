package in.hocg.rabbit.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.named.annotation.InjectNamed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/1/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@InjectNamed
public class OrderDeliveryOrdinaryVo {

    private Long id;
    @ApiModelProperty("订单")
    private Long orderId;
    @ApiModelProperty("配送状态")
    private String status;
    @ApiModelProperty("编号")
    private String encoding;

    @ApiModelProperty("发货人姓名")
    private String deliveryName;
    @ApiModelProperty("发货人电话")
    private String deliveryTel;
    @ApiModelProperty("发货人邮编")
    private String deliveryPostcode;
    @ApiModelProperty("发货人区域编码")
    private String deliveryAdcode;
    @ApiModelProperty("发货人省份/直辖市")
    private String deliveryProvince;
    @ApiModelProperty("发货人城市")
    private String deliveryCity;
    @ApiModelProperty("发货人区")
    private String deliveryRegion;
    @ApiModelProperty("发货人详细地址")
    private String deliveryAddress;
}
