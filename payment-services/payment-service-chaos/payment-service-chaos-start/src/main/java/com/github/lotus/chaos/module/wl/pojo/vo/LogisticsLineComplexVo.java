package com.github.lotus.chaos.module.wl.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import in.hocg.boot.named.autoconfiguration.annotation.InjectNamed;
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
@ApiModel("物流线路")
public class LogisticsLineComplexVo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("省区域编码")
    private String provinceAdcode;
    @ApiModelProperty("市区区域编码")
    private String cityAdcode;
    @ApiModelProperty("县区域编码")
    private String districtAdcode;

    @ApiModelProperty("公司")
    private Long companyId;

    @ApiModelProperty("名称")
    private String title;
    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建者")
    private Long creator;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
}
