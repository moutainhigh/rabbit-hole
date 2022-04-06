package in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by hocgin on 2022/4/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class ElecityVo {
    @ApiModelProperty("排序")
    private String sort;
    @ApiModelProperty("地区名称")
    @JSONField(name = "city_name")
    private String cityName;
    @ApiModelProperty("首字母")
    @JSONField(name = "initial")
    private String initial;
    @ApiModelProperty("是否三要素认证")
    @JSONField(name = "need_ytype")
    private Integer needYtype;
    @ApiModelProperty("是否需要选择城市")
    @JSONField(name = "need_city")
    private Integer needCity;
    @ApiModelProperty("支持的地级市")
    @JSONField(name = "city")
    private List<City> city;

    @Data
    @Accessors(chain = true)
    public static class City {
        @ApiModelProperty("城市名称")
        @JSONField(name = "city_name")
        private String cityName;
        @ApiModelProperty("首字母")
        @JSONField(name = "initial")
        private String initial;
    }
}
