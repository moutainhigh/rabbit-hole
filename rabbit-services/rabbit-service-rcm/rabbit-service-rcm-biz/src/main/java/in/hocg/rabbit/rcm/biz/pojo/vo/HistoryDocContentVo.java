package in.hocg.rabbit.rcm.biz.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/2/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class HistoryDocContentVo {
    @ApiModelProperty("文稿内容对象")
    private Long id;
    @ApiModelProperty("是否草稿")
    private Boolean draft;
    @ApiModelProperty("版本名称")
    private String title;
    @ApiModelProperty("草稿创建时间")
    private LocalDateTime createdAt;
}
