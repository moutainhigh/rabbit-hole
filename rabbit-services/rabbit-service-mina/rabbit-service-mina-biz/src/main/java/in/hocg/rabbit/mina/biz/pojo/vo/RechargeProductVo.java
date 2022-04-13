package in.hocg.rabbit.mina.biz.pojo.vo;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
public class RechargeProductVo implements Serializable {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModelProperty("产品ID")
    private String id;
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModelProperty("类型名称")
    private String className;
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModelProperty("产品名称")
    private String productName;
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModelProperty("价格，下单扣费金额")
    private BigDecimal price;
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModelProperty("备注信息")
    private String desc;

}
