package com.github.lotus.mina.biz.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
}
