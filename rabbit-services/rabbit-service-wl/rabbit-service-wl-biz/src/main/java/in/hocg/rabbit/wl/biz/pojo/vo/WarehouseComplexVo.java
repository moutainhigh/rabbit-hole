package in.hocg.rabbit.wl.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedServiceApi;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.rabbit.common.constant.DistrictLevelConstant;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@ApiModel(description = "物流仓库")
public class WarehouseComplexVo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("名称")
    private String title;
    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("省区域编码")
    private String provinceAdcode;
    @ChaosNamed(idFor = "provinceAdcode", type = ChaosNamedType.DistrictName,
        args = {DistrictLevelConstant.PROVINCE_CODE})
    private String provinceName;
    @ApiModelProperty("市区区域编码")
    private String cityAdcode;
    @ChaosNamed(idFor = "cityAdcode", type = ChaosNamedType.DistrictName,
        args = {DistrictLevelConstant.CITY_CODE})
    private String cityName;
    @ApiModelProperty("县区域编码")
    private String districtAdcode;
    @ChaosNamed(idFor = "districtAdcode", type = ChaosNamedType.DistrictName,
        args = {DistrictLevelConstant.DISTRICT_CODE})
    private String districtName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.UserId2Username)
    private String creatorName;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @ChaosNamed(idFor = "lastUpdater", type = ChaosNamedType.UserId2Username)
    private String lastUpdaterName;

    @ApiModelProperty("物流公司")
    private CompanyComplexVo company;
}
