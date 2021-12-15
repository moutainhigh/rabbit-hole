package in.hocg.rabbit.mall.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/3/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CouponSaveRo {

    @NotBlank(message = "请输入优惠券名称")
    @ApiModelProperty("优惠券名称")
    private String title;

    @NotBlank(message = "请选择优惠券类型")
    @ApiModelProperty("优惠券类型")
    private String couponType;

    @NotBlank(message = "请选择可用类型")
    @ApiModelProperty("可用类型")
    private String useStint;

    @NotBlank(message = "请选择可用平台")
    @ApiModelProperty("可用平台")
    private String usePlatform;

    @ApiModelProperty("优惠券使用说明")
    private String useInstructions;

    @ApiModelProperty("后台备注")
    private String remark;

    @ApiModelProperty("订单最低启用金额")
    private BigDecimal minPoint;

    @NotNull(message = "请输入优惠力度")
    @ApiModelProperty("满减金额/折扣率")
    private BigDecimal credit;

    @ApiModelProperty("优惠上限")
    private BigDecimal ceiling;

    @Size(min = 1, message = "请选择可用商品")
    @ApiModelProperty("可用商品")
    private List<Long> useProductId;
    @Size(min = 1, message = "请选择可用品类")
    @ApiModelProperty("可用品类")
    private List<Long> useProductCategoryId;

}