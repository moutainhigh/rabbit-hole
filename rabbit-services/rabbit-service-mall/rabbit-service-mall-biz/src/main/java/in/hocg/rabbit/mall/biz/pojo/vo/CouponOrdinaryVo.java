package in.hocg.rabbit.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class CouponOrdinaryVo {
    @ApiModelProperty("优惠券名称")
    private String title;
    @ApiModelProperty("折扣方式：[fixed_amt=>满减；scale_amt=>折扣]")
    private String type;
    @ApiModelProperty("使用说明")
    private String useInstructions;
    @ApiModelProperty("优惠券备注")
    private String remark;
    @ApiModelProperty("满减金额/折扣率")
    private BigDecimal credit;
    @ApiModelProperty("优惠上限")
    private BigDecimal ceilingAmt;
}
