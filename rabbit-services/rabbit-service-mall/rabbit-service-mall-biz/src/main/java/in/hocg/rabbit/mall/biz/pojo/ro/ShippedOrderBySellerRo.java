package in.hocg.rabbit.mall.biz.pojo.ro;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.w3c.dom.Node;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by hocgin on 2020/3/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class ShippedOrderBySellerRo {
    @NotNull(message = "发货人姓名不能为空")
    @ApiModelProperty("发货人姓名")
    private String deliveryName;
    @NotNull(message = "发货人电话不能为空")
    @ApiModelProperty("发货人电话")
    private String deliveryTel;
    @NotNull(message = "发货人邮编不能为空")
    @ApiModelProperty("发货人邮编")
    private String deliveryPostcode;
    @NotNull(message = "发货人区域编码不能为空")
    @ApiModelProperty("发货人区域编码")
    private String deliveryAdcode;
    @NotNull(message = "发货人省份不能为空")
    @ApiModelProperty("发货人省份/直辖市")
    private String deliveryProvince;
    @NotNull(message = "发货人城市不能为空")
    @ApiModelProperty("发货人城市")
    private String deliveryCity;
    @NotNull(message = "发货人区不能为空")
    @ApiModelProperty("发货人区")
    private String deliveryRegion;
    @NotNull(message = "发货人详细地址不能为空")
    @ApiModelProperty("发货人详细地址")
    private String deliveryAddress;

    @ApiModelProperty(value = "操作人", hidden = true)
    private Long operatorId;
}
