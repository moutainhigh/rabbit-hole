package in.hocg.rabbit.bmw.biz.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class CreatePayRecordDto {
    @ApiModelProperty("交易单")
    private Long tradeOrderId;
    @ApiModelProperty("支付类型")
    private String payType;
    @ApiModelProperty("支付商户")
    private Long paymentMchId;
    @ApiModelProperty("支付账户")
    private Long payActId;
}
