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
public class CreateAccountDto {
    @ApiModelProperty("接入商户用户")
    private Long userId;
    @ApiModelProperty("接入商户")
    private Long accessMchId;
    @ApiModelProperty("支付商户")
    private Long paymentMchId;
    @ApiModelProperty("使用场景")
    private String useScenes;
    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("账号(支付商户)")
    private String mchAccount;
}
