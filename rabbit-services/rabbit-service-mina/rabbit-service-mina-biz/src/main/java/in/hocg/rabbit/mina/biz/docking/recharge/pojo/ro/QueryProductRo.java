package in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro;

import com.alibaba.fastjson.annotation.JSONField;
import in.hocg.rabbit.mina.biz.support.recharge.RechargeHelper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/4/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class QueryProductRo extends BaseRo {
    @ApiModelProperty("账户ID")
    private String userid = RechargeHelper.USER_ID;
    @ApiModelProperty("产品类型ID,非必须")
    private String type;
    @ApiModelProperty("分类ID,非必须")
    @JSONField(name = "cate_id")
    private String cateId;
}
