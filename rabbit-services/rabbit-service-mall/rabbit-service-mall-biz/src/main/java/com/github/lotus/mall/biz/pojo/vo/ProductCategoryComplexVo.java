package com.github.lotus.mall.biz.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.lotus.chaos.api.NamedType;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/3/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
public class ProductCategoryComplexVo {
    @ApiModelProperty
    private Long id;
    @ApiModelProperty
    private Long parentId;
    @ApiModelProperty
    private String title;
    @ApiModelProperty
    private String remark;
    @ApiModelProperty
    private String imageUrl;

    @ApiModelProperty("开启状态")
    private Boolean enabled;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    private Long creator;
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("最后更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("最后更新时间")
    private Long lastUpdater;
    @Named(idFor = "lastUpdater", type = NamedType.Userid2Nickname)
    private String lastUpdaterName;
}
