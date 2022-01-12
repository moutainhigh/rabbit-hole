package in.hocg.rabbit.mall.biz.pojo.ro;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by hocgin on 2020/3/24.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class UpdateOrderRo {
    @ApiModelProperty("收货人姓名")
    private String receiverName;
    @ApiModelProperty("收货人电话")
    private String receiverTel;
    @ApiModelProperty("收货人邮编")
    private String receiverPostcode;
    @ApiModelProperty("收货人区域编码")
    private String receiverAdcode;
    @ApiModelProperty("收货人省份/直辖市")
    private String receiverProvince;
    @ApiModelProperty("收货人城市")
    private String receiverCity;
    @ApiModelProperty("收货人区")
    private String receiverRegion;
    @ApiModelProperty("收货人详细地址")
    private String receiverAddress;
}
