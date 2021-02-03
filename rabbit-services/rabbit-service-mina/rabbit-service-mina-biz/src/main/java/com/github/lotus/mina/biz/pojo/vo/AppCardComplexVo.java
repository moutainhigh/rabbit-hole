package com.github.lotus.mina.biz.pojo.vo;

import com.github.lotus.chaos.api.ChaosNamedAPI;
import com.github.lotus.chaos.api.NamedType;
import in.hocg.boot.named.autoconfiguration.annotation.Named;
import in.hocg.boot.named.autoconfiguration.annotation.UseNamedService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/1/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ApiModel
public class AppCardComplexVo {
    private String title;
    private String logoUrl;
    private String remark;
    private List<String> tags = Collections.emptyList();
    private List<String> viewUrls = Collections.emptyList();
    private AppCardComplexVo.Href href;
    @ApiModelProperty("置顶状态")
    private Boolean isTop;

    @Data
    @Accessors(chain = true)
    @ApiModel(description = "链接")
    public static class Href {
        public AppCardComplexVo.Href.Mini mini;

        @Data
        @Accessors(chain = true)
        @ApiModel(description = "小程序")
        public static class Mini {
            private String appid;
            private String path;
        }
    }

    @ApiModelProperty("创建者")
    private Long creator;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "creator", type = NamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("更新者")
    private Long lastUpdater;
    @UseNamedService(ChaosNamedAPI.class)
    @Named(idFor = "lastUpdater", type = NamedType.Userid2Nickname)
    private String lastUpdaterName;
    @ApiModelProperty("更新时间")
    private LocalDateTime lastUpdatedAt;
}
