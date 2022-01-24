package in.hocg.rabbit.com.biz.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class UserAddressClientComplexVo {
    @ApiModelProperty("姓名")
    private Long id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("电话")
    private String tel;
    @ApiModelProperty("邮编")
    private String postcode;
    @ApiModelProperty("区域编码")
    private String adcode;
    @ApiModelProperty("省份")
    private String province;
    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("区")
    private String region;
    @ApiModelProperty("详细地址")
    private String address;
    @ApiModelProperty("是否默认地址")
    private Boolean defaultFlag;
}
