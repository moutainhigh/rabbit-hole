package in.hocg.rabbit.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import in.hocg.rabbit.mall.api.named.MallNamed;
import in.hocg.rabbit.mall.api.named.MallNamedType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2022/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@InjectNamed
public class CouponOrdinaryVo {
    @ApiModelProperty("优惠券名称")
    private String title;
    @ApiModelProperty("折扣方式")
    private String type;
    @MallNamed(idFor = "type", type = MallNamedType.DataDictName, args = {MallDataDictKeys.COUPON_TYPE})
    private String typeName;

    @ApiModelProperty("使用说明")
    private String useInstructions;
    @ApiModelProperty("优惠券备注")
    private String remark;
    @ApiModelProperty("满减金额/折扣率")
    private BigDecimal credit;
    @ApiModelProperty("优惠上限")
    private BigDecimal ceilingAmt;
}
