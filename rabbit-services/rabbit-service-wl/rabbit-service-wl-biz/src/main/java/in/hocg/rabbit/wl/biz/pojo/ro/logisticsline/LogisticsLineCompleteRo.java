package in.hocg.rabbit.wl.biz.pojo.ro.logisticsline;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.CompleteRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class LogisticsLineCompleteRo extends CompleteRo {
    @ApiModelProperty("关键字")
    private String keyword;
    @ApiModelProperty("物流公司ID")
    private Long companyId;
    @ApiModelProperty("仓库ID")
    private Long warehouseId;
}
