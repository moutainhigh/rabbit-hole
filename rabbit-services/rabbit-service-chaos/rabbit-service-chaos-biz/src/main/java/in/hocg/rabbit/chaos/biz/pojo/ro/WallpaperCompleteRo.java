package in.hocg.rabbit.chaos.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.CompleteRo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/12/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class WallpaperCompleteRo extends CompleteRo {
    @ApiModelProperty("关键字")
    private String keyword;
}
