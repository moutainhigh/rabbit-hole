package in.hocg.rabbit.mall.biz.pojo.ro;

import in.hocg.boot.validation.annotation.EnumRange;
import in.hocg.boot.validation.group.Insert;
import in.hocg.boot.validation.group.Update;
import in.hocg.rabbit.mall.api.enums.coupon.CouponType;
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

    @NotNull(groups = {Insert.class})
    @NotBlank(groups = {Insert.class, Update.class}, message = "请输入优惠券名称")
    @ApiModelProperty("优惠券名称")
    private String title;

    @NotNull(groups = {Insert.class})
    @EnumRange(groups = {Insert.class, Update.class}, enumClass = CouponType.class, message = "优惠券类型错误")
    @NotBlank(message = "请选择优惠券类型")
    @ApiModelProperty("优惠券类型")
    private String type;

    @ApiModelProperty("优惠券使用说明")
    private String useInstructions;

    @ApiModelProperty("后台备注")
    private String remark;

    @NotNull(groups = {Insert.class, Update.class}, message = "请输入优惠力度")
    @ApiModelProperty("满减金额/折扣率")
    private BigDecimal credit;

    @ApiModelProperty("优惠上限")
    private BigDecimal ceilingAmt;

    @NotNull(groups = {Insert.class})
    @Size(min = 1, groups = {Insert.class, Update.class}, message = "请选择限制规则")
    @ApiModelProperty("限制规则")
    private List<Long> stintRule;

}
