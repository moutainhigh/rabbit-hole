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
@ApiModel(description = "小游戏")
public class MinaGameCardComplexVo {
    private Long id;
    private String title;
    private String logoUrl;
    private String remark;
    private String gameUrl;
    private List<String> tags = Collections.emptyList();
    private List<String> viewUrls = Collections.emptyList();
    private Integer priority;
    @ApiModelProperty("启用状态")
    private Boolean enabled;

}
