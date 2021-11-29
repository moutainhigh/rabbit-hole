package in.hocg.rabbit.bmw.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/8/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class PaySceneItemVo {
    @ApiModelProperty("支付项")
    private Long id;
    @ApiModelProperty("支付商户")
    private Long paymentMchId;
    @ApiModelProperty("支付选项名称")
    private String title;
    @ApiModelProperty("支付类型")
    private String payType;
}
