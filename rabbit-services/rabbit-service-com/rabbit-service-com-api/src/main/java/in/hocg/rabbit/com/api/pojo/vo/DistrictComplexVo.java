package in.hocg.rabbit.com.api.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/4/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class DistrictComplexVo {
    private Long id;
    private Long parentId;
    @ApiModelProperty("区域编码")
    private String adcode;
    @ApiModelProperty("城市编码")
    private String cityCode;
    @ApiModelProperty("名称")
    private String title;
}
