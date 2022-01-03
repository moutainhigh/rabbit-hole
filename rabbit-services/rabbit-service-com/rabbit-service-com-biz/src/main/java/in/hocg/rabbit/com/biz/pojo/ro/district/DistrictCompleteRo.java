package in.hocg.rabbit.com.biz.pojo.ro.district;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.CompleteRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/11/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class DistrictCompleteRo extends CompleteRo {
    @ApiModelProperty("关键字")
    private String keyword;
}
