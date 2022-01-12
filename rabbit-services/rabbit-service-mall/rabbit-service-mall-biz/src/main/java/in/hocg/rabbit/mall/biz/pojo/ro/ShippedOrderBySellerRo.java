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
    @NotNull(message = "发货地址错误")
    @ApiModelProperty("发货地址")
    private Long userAddressId;
    @ApiModelProperty(value = "操作人", hidden = true)
    private Long operatorId;
}
