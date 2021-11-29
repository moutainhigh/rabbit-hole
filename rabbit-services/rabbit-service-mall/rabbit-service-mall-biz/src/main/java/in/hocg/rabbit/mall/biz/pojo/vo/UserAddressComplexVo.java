package in.hocg.rabbit.mall.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/4/18.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
public class UserAddressComplexVo {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("收件人姓名")
    private String name;
    @ApiModelProperty("收件人手机号")
    private String phone;
    @ApiModelProperty("邮政编码")
    private String postCode;
    @ApiModelProperty("省份")
    private String province;
    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("区")
    private String region;
    @ApiModelProperty("详细地址(街道)")
    private String address;
    @ApiModelProperty("区域编码")
    private String adCode;
    @ApiModelProperty("是否默认")
    private Boolean isDefault;
}
