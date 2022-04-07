package in.hocg.rabbit.mina.api.pojo.vo;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class RechargeProductVo {
    @ApiModelProperty("产品ID")
    private String id;
    @ApiModelProperty("类型名称")
    private String className;
    @ApiModelProperty("产品名称")
    private String productName;
    @ApiModelProperty("价格，下单扣费金额")
    private BigDecimal price;
    @ApiModelProperty("备注信息")
    private String desc;

    public String getTitle() {
        return new StringJoiner(",")
            .add(this.getClassName())
            .add(this.getDesc())
            .add(this.getProductName())
            .add(StrUtil.toString(this.getPrice()))
            .toString();
    }
}
