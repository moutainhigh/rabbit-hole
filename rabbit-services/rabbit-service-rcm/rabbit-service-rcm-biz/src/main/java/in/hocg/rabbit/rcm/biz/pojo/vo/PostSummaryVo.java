package in.hocg.rabbit.rcm.biz.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/4/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class PostSummaryVo implements Serializable {
    @ApiModelProperty("观看次数")
    private Long viewCount;
    @ApiModelProperty("喜欢次数")
    private Long likeCount;
}
