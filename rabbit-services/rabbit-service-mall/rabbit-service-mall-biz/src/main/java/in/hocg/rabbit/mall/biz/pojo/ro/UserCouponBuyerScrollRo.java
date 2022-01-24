package in.hocg.rabbit.mall.biz.pojo.ro;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.ScrollRo;
import in.hocg.boot.validation.annotation.EnumRange;
import in.hocg.rabbit.mall.api.enums.coupon.UserCouponStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/1/20
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class UserCouponBuyerScrollRo extends ScrollRo {
    @ApiModelProperty("过期状态")
    private Boolean expiredFlag;
    @ApiModelProperty("优惠券状态")
    @EnumRange(enumClass = UserCouponStatus.class, message = "优惠券状态不合法")
    private String status;
    @ApiModelProperty(value = "所属用户", hidden = true)
    private Long ownerUserId;
}
