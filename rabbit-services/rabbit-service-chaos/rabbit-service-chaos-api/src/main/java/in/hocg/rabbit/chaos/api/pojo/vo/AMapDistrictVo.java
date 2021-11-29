package in.hocg.rabbit.chaos.api.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by hocgin on 2020/4/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class AMapDistrictVo {
    @ApiModelProperty("城市编码")
    private String citycode;
    @ApiModelProperty("区域编码")
    private String adcode;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("级别")
    private String level;
    @ApiModelProperty("中心经纬度")
    private String center;
    @ApiModelProperty("子节点")
    private List<AMapDistrictVo> districts;
}
