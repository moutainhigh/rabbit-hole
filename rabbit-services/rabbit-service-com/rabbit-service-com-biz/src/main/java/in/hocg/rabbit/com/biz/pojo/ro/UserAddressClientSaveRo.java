package in.hocg.rabbit.com.biz.pojo.ro;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.validation.group.Insert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2022/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class UserAddressClientSaveRo {
    @NotNull(groups = {Insert.class}, message = "地址类型不能为空")
    @ApiModelProperty("地址类型")
    private String type;
    @NotNull(groups = {Insert.class}, message = "姓名不能为空")
    @ApiModelProperty("姓名")
    private String name;
    @NotNull(groups = {Insert.class}, message = "电话不能为空")
    @ApiModelProperty("电话")
    private String tel;
    @NotNull(groups = {Insert.class}, message = "邮编不能为空")
    @ApiModelProperty("邮编")
    private String postcode;
    @NotNull(groups = {Insert.class}, message = "区域编码不能为空")
    @ApiModelProperty("区域编码")
    private String adcode;
    @NotNull(groups = {Insert.class}, message = "详细地址不能为空")
    @ApiModelProperty("详细地址")
    private String address;
    @NotNull(groups = {Insert.class}, message = "省份不能为空")
    @ApiModelProperty("省份")
    private String province;
    @NotNull(groups = {Insert.class}, message = "城市不能为空")
    @ApiModelProperty("城市")
    private String city;
    @NotNull(groups = {Insert.class}, message = "区不能为空")
    @ApiModelProperty("区")
    private String region;
    @ApiModelProperty("是否默认地址")
    private Boolean defaultFlag;
    @ApiModelProperty(value = "所属用户", hidden = true)
    private Long ownerUserId;
}
